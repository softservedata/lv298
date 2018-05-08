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
		//System.out.println("getFromOS(String propertyName) " + getFromOS(propertyNameOS));
		//System.out.println("getFromMaven(String propertyName) " + getFromMaven(propertyNameMaven));
		String result = getFromMaven(propertyNameMaven);
		return ((result == null) || (result.isEmpty()) || result.equals("null")) ? getFromOS(propertyNameOS) : result;
	}

}