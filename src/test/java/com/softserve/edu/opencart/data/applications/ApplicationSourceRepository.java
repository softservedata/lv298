package com.softserve.edu.opencart.data.applications;

public final class ApplicationSourceRepository {

    private ApplicationSourceRepository() {
    }

    public static IApplicationSource defaultParameters() {
	return EpizyChrome();
    }

    public static IApplicationSource EpizyChrome() {
	return new ApplicationSource("ChromeTemporary", // "ChromeProfile", // "ChromeTemporary", // "ChromeProfile",
		"C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe", 10, "https://nazaronoc.000webhostapp.com/");
    }

}
