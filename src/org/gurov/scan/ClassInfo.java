package org.gurov.scan;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ClassInfo {

	private Class<?> clazz;
	private String md5;

	private List<Annotation> annotationsClazz = new ArrayList<>();
	private List<MethodInfo> methods = new ArrayList<>();
	private List<FieldInfo> fields = new ArrayList<>();

	public ClassInfo(Class<?> clazz, String md5) {
		// super();
		this.clazz = clazz;
		this.md5 = md5;

		// initAnnotationsClazz();
		// initMethod();
		// initField();
	}

	// public Class<?> getClazz() {
	// return clazz;
	// }
	//
	// public String getName() {
	// return clazz.getSimpleName();
	// }
	//
	// public String getMD5() {
	// return md5;
	// }
	//
	// public List<Annotation> getAnnotationsClazz() {
	// return annotationsClazz;
	// }
	//
	// public List<MethodInfo> getMethod() {
	// return methods;
	// }
	//
	// public List<FieldInfo> getField() {
	// return fields;
	// }
	//
	// private void initAnnotationsClazz() {
	// Annotation[] annotationsClazz = clazz.getAnnotations();
	// if (annotationsClazz.length > 0) {
	// this.annotationsClazz.addAll(Arrays.asList(annotationsClazz));
	// }
	// }
	//
	// private void initMethod() {
	// Method[] methods = clazz.getDeclaredMethods();
	// if (methods.length > 0) {
	// for (Method method : methods) {
	// this.methods.add(new MethodInfo(method));
	// }
	// }
	// }
	//
	// private void initField() {
	// Field[] fields = clazz.getDeclaredFields();
	// if (fields.length > 0) {
	// for (Field field : fields) {
	// this.fields.add(new FieldInfo(field));
	// }
	// }
	// }

}
