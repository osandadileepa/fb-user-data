package com.osanda.roihunter.fbuserdata.util;

import java.io.File;
import java.lang.reflect.Field;

public class DirectoryUtil {

	public final static File DATA = new File("data");
	
	public final static File PROFILE_PIC = new File(DATA, "profile_pic");
	
	public final static File PHOTOS = new File(DATA, "photos");

	public static void createDirectories() {
		Field[] fields = DirectoryUtil.class.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			if (f.getType().isAssignableFrom(File.class)) {
				try {
					File file = (File) f.get(File.class);
					if (!file.exists()) {
						file.mkdirs();
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} // End for loop
	}// End createDirectories ()

}
