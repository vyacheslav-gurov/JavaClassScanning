package org.gurov.scan;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.gurov.scan.handler.GetClasses;
import org.gurov.scan.handler.PrintClasses;
import org.gurov.scan.handler.SaveClasses;
import org.gurov.scan.info.ClassInfo;
import org.gurov.scan.intarfaces.IGetClasses;
import org.gurov.scan.intarfaces.IPrintClasses;
import org.gurov.scan.intarfaces.ISaveClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Main {

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {

		List<ClassInfo> classes = new ArrayList<>();
		try {
			IGetClasses getClasses = new GetClasses();
			classes = getClasses.get("org.gurov.scan");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		IPrintClasses printClasses = new PrintClasses();
		// printClasses.print(classes);

		Gson gson = new GsonBuilder()
				// .excludeFieldsWithoutExposeAnnotation()
				.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
				// .serializeNulls()
				.create();
		java.lang.reflect.Type type = new TypeToken<List<ClassInfo>>() {
		}.getType();
		String json = gson.toJson(classes, type);
		List<ClassInfo> read = gson.fromJson(json, type);
		ISaveClasses saveClasses = new SaveClasses();
		// saveClasses.save(classes);
	}

}
