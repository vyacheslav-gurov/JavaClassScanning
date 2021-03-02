package org.gurov.scan.info;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.List;

public interface Info<T extends AccessibleObject> {

	public T get();

	public List<Annotation> getAnnotations();
}
