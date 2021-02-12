package org.gurov.scan;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodInfo {

	private Method method;
	private List<Annotation> annotationsMethod = new ArrayList<>();

	public MethodInfo(Method method) {
		super();
		this.method = method;

		initAnnotationsMethod();
	}

	public Method getMethod() {
		return method;
	}

	public String getName() {
		return method.getName();
	}

	public List<Annotation> getAnnotationsMethod() {
		return annotationsMethod;
	}

	private void initAnnotationsMethod() {
		Annotation[] annotations = method.getAnnotations();
		if (annotations.length > 0) {
			this.annotationsMethod.addAll(Arrays.asList(annotations));
		}
	}

}
