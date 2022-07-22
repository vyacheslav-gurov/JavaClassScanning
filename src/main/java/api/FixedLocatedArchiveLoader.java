package api;

import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

import static api.UnzipJar.unzipJar;

class FixedLocatedArchiveLoader extends URLClassLoader {

    private List<String> appliedPackages;
    private List<String> notAppliedPackages;
    private final static Map<Path, Boolean> unzipedJars = new ConcurrentHashMap<>();


    protected FixedLocatedArchiveLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    private List<String> getAppliedPackages() {
        if (appliedPackages == null) {
            this.appliedPackages = new ArrayList<String>();
        }
        return this.appliedPackages;
    }

    private List<String> getNotAppliedPackages() {
        if (notAppliedPackages == null) {
            this.notAppliedPackages = new ArrayList<String>();
        }
        return this.notAppliedPackages;
    }

    public void addAppliedPackages(String packageName) {
        getAppliedPackages().add(packageName);
    }

    public void addNotAppliedPackages(String packageName) {
        getNotAppliedPackages().add(packageName);
    }

    private boolean applyFixedLoad(String className) {
        return inApplyFixedLoad(className) && notApplyFixedLoad(className);
    }

    private boolean notApplyFixedLoad(String className) {
        boolean allpy = true;
        for (String packageName : getNotAppliedPackages()) {
            if (className.contains(packageName)) {
                allpy = false;
                break;
            }
        }
        return allpy;
    }

    private boolean inApplyFixedLoad(String className) {
        for (String packageName : getAppliedPackages()) {
            if (className.contains(packageName)) return true;
        }
        return false;
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (applyFixedLoad(name)) {
            // construct path with .class file
            String classFile = name.replaceAll("\\.", "/") + ".class";
            // Read all entries of defined JARs and try to found given class
            URL[] urls = getURLs();
            int i = 0;
            Class<?> searchedClass = null;
            while (searchedClass == null && i < urls.length) {
                URL url = urls[i];
                i++;
                searchedClass = findByFile(url, classFile, name);
            }
            return searchedClass;
        }
        return super.findClass(name);
    }

    @SneakyThrows
    private Class<?> findByFile(URL url, String classFile, String name) {
        JarFile jarFile = null;

        /**
         * JarFile is constructed directly from File instance. After
         * we get ZipEntry which is, in fact, the element composing the JAR.
         * If entry is found, we construct class instance from read bytes.
         * The bytes are read in readClassBytesFromFile method.
         */
        List<File> jars = getFiles(url.getFile());
        for (File jar : jars) {
            try {
                addURL(jar.toURL());
                jarFile = new JarFile(jar);
                ZipEntry entry = jarFile.getEntry(classFile);
                if (entry != null) {
                    byte[] entryBytes = readClassBytesFromFile(jar, classFile);
                    return defineClass(name, entryBytes, 0, entryBytes.length);
                }
            } finally {
                if (jarFile != null) {
                    try {
                        jarFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    private List<File> getFiles(String url) throws IOException {
        if (url.contains(".jar!")) {
            return getSubJars(url);
        } else {
            if (!url.endsWith(".jar")) {
                return Files.walk(Path.of(url.substring(0, url.length() - 3))).map(Path::toFile).collect(Collectors.toList());
            } else {
                return List.of(new File(url));
            }
        }
    }

    private List<File> getSubJars(String url) throws IOException {
        String[] split = url.split("!");
        Path path = Path.of(split[0].replace("file:", ""));
        String impl = split[1].substring(1);

        boolean needAllInFolder = !impl.endsWith(".jar");
        Path source = path.getParent().resolve(path.getFileName().toString().replace(".", "_"));
        if (unzipedJars.get(path) == null) {
            unzipJar(source.toString(), path.toString());
            unzipedJars.put(path, true);
        }

        Path sourcePath = Path.of(source + "/" + impl);
        if (needAllInFolder) {
            return Files.walk(sourcePath).filter(f -> !f.toFile().isDirectory() && f.getFileName().toString().endsWith(".jar")).map(Path::toFile).collect(Collectors.toList());
        }
        return List.of(sourcePath.toFile());
    }

    /**
     * Reads bytes from given JAR file. The reading is based on special URL implementation
     * dedicated to JARs access. It starts by jar: protocol and continues with the name of the file.
     * The final !/ separates this two parts from the name of searched class. For example,
     * this URL jar:file://home/bartosz/tmp/jars/spring-core-4.0.0.RELEASE.jar!/org/springframework/util/PropertyPlaceholderHelper.class
     * - jar: marks the URL as JAR URL
     * - file://home/bartosz/tmp/jars/spring-core-4.0.0.RELEASE.jar: address of the JAR file containing searched class
     * - !/org/springframework/util/PropertyPlaceholderHelper.class: the name of searched class
     *
     * @param jar       File containing searched class.
     * @param className Searched class.
     * @return Array of bytes with searched class (if error, the array is empty).
     */
    @SneakyThrows
    private byte[] readClassBytesFromFile(File jar, String className) {
        InputStream stream = null;
        try {
            URL urlTmp = new URL("jar:" + getURI(jar) + "!/" + className);
            JarURLConnection jarUrl = (JarURLConnection) urlTmp.openConnection();
            stream = jarUrl.getInputStream();
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            return bytes;
        } catch (Throwable throwable) {
            return new byte[]{};
        }

    }

    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (applyFixedLoad(name)) {
            Class<?> searchedClass = findClass(name);
            if (resolve) {
                resolveClass(searchedClass);
            }
            return searchedClass;
        }
        return super.loadClass(name, resolve);
    }


    /**
     * Taken from Tomcat7 class loader: org.apache.catalina.loader.WebappClassLoader
     */
    private URL getURI(File file) throws MalformedURLException, IOException {
        File realFile = file;
        realFile = realFile.getCanonicalFile();
        return realFile.toURI().toURL();
    }
}
