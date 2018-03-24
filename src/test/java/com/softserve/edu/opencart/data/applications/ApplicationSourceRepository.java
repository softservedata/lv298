package com.softserve.edu.opencart.data.applications;

public final class ApplicationSourceRepository {

    private ApplicationSourceRepository() {
    }

    public static IApplicationSource defaultParameters() {
	return openCartChrome();
    }

    public static IApplicationSource openCartChrome() {
	return new ApplicationSource(
	        "ChromeTemporary", // "ChromeProfile", // "ChromeTemporary", // "ChromeProfile",
    		"C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe",
            10,
            "https://nazaronoc.000webhostapp.com/");
    }

    public static IApplicationSource openCartFirefox(){
        return new ApplicationSource(
                "Firefox",
                "C:/Program Files/Mozilla Firefox/geckodriver.exe",
                10,
                "https://nazaronoc.000webhostapp.com/");
    }
}
