package org.gurov.scan.info;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractInfo<T extends AccessibleObject> implements Info<T> {

	protected T object;
	private List<Annotation> annotationsObject = new ArrayList<>();

	public AbstractInfo(T object) {
		this.object = object;

		initAnnotationsObject();
	}

	@Override
	public T get() {
		return object;
	}

	@Override
	public List<Annotation> getAnnotations() {
		return annotationsObject;
	}

	protected void initAnnotationsObject() {
		Annotation[] annotations = object.getAnnotations();
		if (annotations.length > 0) {
			this.annotationsObject.addAll(Arrays.asList(annotations));
		}
	}
}
