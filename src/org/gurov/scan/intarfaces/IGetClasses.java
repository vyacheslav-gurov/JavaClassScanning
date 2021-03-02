package org.gurov.scan.intarfaces;

import java.io.IOException;
import java.util.List;

import org.gurov.scan.info.ClassInfo;

public interface IGetClasses {

	public List<ClassInfo> get(String packageName) throws ClassNotFoundException, IOException;
}
