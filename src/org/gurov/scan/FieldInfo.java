package org.gurov.scan;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldInfo {

	private Field field;
	private List<Annotation> annotationsField = new ArrayList<>();

	public FieldInfo(Field field) {
		super();
		this.field = field;

		initAnnotationsField();
	}

	public Field getField() {
		return field;
	}

	public String getName() {
		return field.getName();
	}

	public List<Annotation> getAnnotationsField() {
		return annotationsField;
	}

	private void initAnnotationsField() {
		Annotation[] annotations = field.getAnnotations();
		if (annotations.length > 0) {
			this.annotationsField.addAll(Arrays.asList(annotations));
		}
	}
}
