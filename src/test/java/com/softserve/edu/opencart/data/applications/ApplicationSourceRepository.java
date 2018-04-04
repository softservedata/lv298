package com.softserve.edu.opencart.data.applications;

import com.softserve.edu.Sel;

public final class ApplicationSourceRepository {
  
    private ApplicationSourceRepository() {
    }

    public static IApplicationSource defaultParameters() {
        return EpizyChrome();
    }

    public static IApplicationSource EpizyChrome() {
        return new ApplicationSource("ChromeTemporary", // "ChromeTemporary", // "ChromeProfile",
        		ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1),
                //"C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe",
                10, 
                "http://atqc-shop.epizy.com"
                //"http://nazaronoc.000webhostapp.com/"
                );
    }
    
}
