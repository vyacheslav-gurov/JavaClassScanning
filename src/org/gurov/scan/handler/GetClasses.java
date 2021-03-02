package org.gurov.scan.handler;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.gurov.scan.info.ClassInfo;
import org.gurov.scan.intarfaces.IGetClasses;

public class GetClasses implements IGetClasses {

	/**
	 * Scans all classes accessible from the context class loader which belong to
	 * the given package and subpackages.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Override
	public List<ClassInfo> get(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		System.out.println("Пакет: " + packageName);
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		List<ClassInfo> classes = new ArrayList<>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}

		return classes;
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 * 
	 * Рекурсивный метод для поиска всех классов в заданной директории.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base
	 *                    directory
	 * @return The list of ClassInfo
	 * @throws ClassNotFoundException
	 */
	private static List<ClassInfo> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<ClassInfo> classes = new ArrayList<>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
				Class<?> clazz = Class.forName(className);
				String md5 = getMD5(clazz.getResource(clazz.getSimpleName() + ".class"));
				ClassInfo classInfo = new ClassInfo(clazz, md5);
				classes.add(classInfo);
			}
		}
		return classes;
	}

	private static String getMD5(URL s) {
		if (s == null) {
			return null;
		}

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(Files.readAllBytes(Paths.get(s.toURI())));
			byte[] digest = md.digest();
			return DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
