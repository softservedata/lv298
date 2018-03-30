package com.softserve.edu.opencart.data.applications;

public final class ApplicationSourceRepository {
  
    private ApplicationSourceRepository() {
    }

    public static IApplicationSource defaultParameters() {
        return EpizyChrome();
    }

    public static IApplicationSource EpizyChrome() {
        return new ApplicationSource("ChromeTemporary", // "ChromeTemporary", // "ChromeProfile",
                "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe",
                10, 
                //"http://atqc-shop.epizy.com"
                "http://nazaronoc.000webhostapp.com/"
                );
    }
    
}
