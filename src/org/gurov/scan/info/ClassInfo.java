package org.gurov.scan.info;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassInfo {

	private Class<?> clazz;
	private String md5;

	private List<Annotation> annotationsClazz = new ArrayList<>();
	private List<Info<Method>> methods = new ArrayList<>();
	private List<Info<Field>> fields = new ArrayList<>();

	public ClassInfo(Class<?> clazz, String md5) {
		this.clazz = clazz;
		this.md5 = md5;

		// initAnnotationsObject();
		initMethod();
		initField();
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public String getName() {
		return clazz.getSimpleName();
	}

	public String getMD5() {
		return md5;
	}

	public List<Annotation> getAnnotationsClazz() {
		return annotationsClazz;
	}

	public List<Info<Method>> getMethod() {
		return methods;
	}

	public List<Info<Field>> getField() {
		return fields;
	}

	private void initMethod() {
		Method[] methods = clazz.getDeclaredMethods();
		if (methods.length <= 0) {
			return;
		}

		for (Method method : methods) {
			this.methods.add(new InfoImpl<Method>(method));
		}
	}

	private void initField() {
		Field[] fields = clazz.getDeclaredFields();
		if (fields.length <= 0) {
			return;
		}

		for (Field field : fields) {
			this.fields.add(new InfoImpl<Field>(field));
		}
	}

}
