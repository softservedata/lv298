package com.softserve.edu.opencart.tools;

public final class SystemPropertyUtils {

	private SystemPropertyUtils() {
	}
	
	public static String getFromOS(String propertyName) {
		return System.getenv(propertyName);
	}

	public static String getFromMaven(String propertyName) {
		return System.getProperty(propertyName);
	}

	public static String getExistingProperty(String propertyNameMaven, String propertyNameOS) {
		String result = getFromMaven(propertyNameMaven);
		return ((result == null) || (result.isEmpty()) || result.equals("null")) ? getFromOS(propertyNameOS) : result;
	}

}