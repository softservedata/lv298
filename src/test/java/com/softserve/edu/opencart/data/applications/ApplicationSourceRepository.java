package com.softserve.edu.opencart.data.applications;

public final class ApplicationSourceRepository {
    
    private static volatile ApplicationSourceRepository instance = null;
    
    // *********Constructor*********
    private ApplicationSourceRepository() {
    }
    
    public static ApplicationSourceRepository get() {
        if (instance == null) {
            synchronized (ApplicationSourceRepository.class) {
                if (instance == null) {
                    instance = new ApplicationSourceRepository();
                }
            }
        }
        return instance;
    }
    
    // *********Repository*********
    public static IApplicationSource defaultParameters() {
        return EpizyChrome();
    }

    public static IApplicationSource EpizyChrome() {
        return ApplicationSource.get()
                .setBrowserName("ChromeTemporary")
                .setDriverPath("C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe")
                .setImplicitWaitTimeOut(10)
                .setBaseUrl("http://setopencart.epizy.com")
                //.setBaseUrl("http://atqc-shop.epizy.com")
                .build();
    }
    
    public static IApplicationSource EpizyChromeProfile() {
        return ApplicationSource.get()
                .setBrowserName("ChromeProfile")
                .setDriverPath("C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe")
                .setImplicitWaitTimeOut(10)
                .setBaseUrl("http://setopencart.epizy.com")
                //.setBaseUrl("http://atqc-shop.epizy.com")
                .build();
    }
    
}
