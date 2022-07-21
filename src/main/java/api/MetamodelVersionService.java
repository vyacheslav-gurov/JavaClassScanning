package api;

import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

class MetamodelVersionService {

    private final static String DEFAULT = "2022.3";
    private final static String VERSION_FOLDER = "metamodel-impl";

    protected static String getCurrentVersion(String path) {
        File file = Path.of(path).resolve("schemas").toFile();
        if (!file.exists()) {
            return null;
        }
        String[] directories = file.list((current, name) -> new File(current, name).isDirectory());

        String fullVersion = Arrays.stream(directories != null ? directories : new String[0]).findFirst().orElse(DEFAULT);
        return getMajorVersionName(fullVersion);
    }

    protected static URL getVersionFolded(ClassLoader classLoader, String version) {
        return classLoader.getResource(VERSION_FOLDER + "/" + version);
    }

    protected static String getLastVersion(ClassLoader classLoader) {
        int intVersion = getAllVersions(classLoader).stream()
                .filter(str -> !str.equals("SNAPSHOT"))
                .map(Integer::parseInt)
                .mapToInt(v -> v)
                .max()
                .orElseThrow(NoSuchElementException::new);
        return String.valueOf(intVersion);
    }

    @SneakyThrows
    protected static List<String> getAllVersions(ClassLoader classLoader) {
        URL resource = classLoader.getResource(MetamodelVersionService.VERSION_FOLDER);

        String[] split = Objects.requireNonNull(resource).getPath().split("!");

        List<String> listFolders = new ArrayList<>();

        ZipInputStream zip = new ZipInputStream(new URL(split[0]).openStream());
        zip.getNextEntry();
        final String searchFolder = split[1].substring(1);
        while (true) {
            ZipEntry e = zip.getNextEntry();
            if (e == null)
                break;
            String name = e.getName();
            if (name.startsWith(searchFolder)) {
                listFolders.add(name);
            }
        }

        return listFolders.stream()
                .filter(f -> !f.contains("."))
                .filter(f -> !f.equals(searchFolder + "/"))
                .map(m -> m.replace(searchFolder, "").replace("/", ""))
                .distinct().collect(Collectors.toList());
    }

    private static String getMajorVersionName(String version) {
        final String snapshot = "SNAPSHOT";
        if (version.contains(snapshot)) {
            return snapshot;
        }
        String[] split = version.split("\\.");
        if (split.length < 2) {
            return split[0];
        }
        return split[0] + split[1];

    }
}
