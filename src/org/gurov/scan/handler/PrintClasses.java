package org.gurov.scan.handler;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Collectors;

import org.gurov.scan.ClassInfo;
import org.gurov.scan.FieldInfo;
import org.gurov.scan.MethodInfo;
import org.gurov.scan.intarfaces.IPrintClasses;

public class PrintClasses implements IPrintClasses {

	@Override
	public void print(List<ClassInfo> classes) {
		for (ClassInfo clazz : classes) {

			System.out.println("--Сам класс: " + clazz.getName() + " MD5: " + clazz.getMD5());

			printClassAnnotations(clazz);

			printField(clazz);

			printMethod(clazz);

			System.out.println("");
		}
	}

	private void printClassAnnotations(ClassInfo clazz) {
		if (clazz.getAnnotationsClazz().size() > 0) {
			String result = clazz.getAnnotationsClazz().stream().map(n -> String.valueOf(n))
					.collect(Collectors.joining(", ", "{", "}"));
			System.out.println("--Анатации класса: " + result);
		}
	}

	private void printMethod(ClassInfo clazz) {
		if (clazz.getMethod().size() > 0) {
			for (MethodInfo method : clazz.getMethod()) {
				if (method.getMethod().isSynthetic()) {
					continue;
				}
				System.out.println("----Метод класса: " + method.getName() + " (модификатор(ы): "
						+ Modifier.toString(method.getMethod().getModifiers()) + ")");

				printMethodAnnotations(method);
			}
		}
	}

	private void printMethodAnnotations(MethodInfo method) {
		if (method.getAnnotationsMethod().size() > 0) {
			String result = method.getAnnotationsMethod().stream().map(m -> m.toString())
					.collect(Collectors.joining(", ", "{", "}"));
			System.out.println("------Аннатации метода: '" + method.getName() + "': " + result);
		}
	}

	private void printField(ClassInfo clazz) {
		if (clazz.getField().size() > 0) {
			String result = clazz.getField().stream().map(n -> {
				return printFieldAnnotations(n) + n.getName();
			}).collect(Collectors.joining(", ", "{", "}"));
			System.out.println("----Поля класса: " + result);
		}
	}

	private String printFieldAnnotations(FieldInfo fieldInfo) {
		return fieldInfo.getAnnotationsField().stream().map(m -> m.toString() + " ").collect(Collectors.joining());
	}

}
