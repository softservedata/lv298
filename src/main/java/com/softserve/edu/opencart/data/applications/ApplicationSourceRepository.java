package com.softserve.edu.opencart.data.applications;

public final class ApplicationSourceRepository {

    private static volatile ApplicationSourceRepository instance = null;
    private static final int IMPLICIT_WAIT_TIME_OUT = 10;

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
        return epizyChrome();
    }

    public static IApplicationSource epizyChrome() {
        return ApplicationSource.get()
                .setBrowserName("ChromeTemporary") //"ChromeProfile"
                .setDriverPath(ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1))
                .setBaseUrl("http://setopencart.epizy.com")
                //.setImplicitWaitTimeOut(IMPLICIT_WAIT_TIME_OUT)
                //.setBaseUrl("http://atqc-shop.epizy.com")
                .build();
    }
}
