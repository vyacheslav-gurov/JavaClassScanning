package org.gurov.scan.info;

import java.lang.reflect.AccessibleObject;

public class InfoImpl<T extends AccessibleObject> extends AbstractInfo<T> {

	public InfoImpl(T object) {
		super(object);
	}

}
