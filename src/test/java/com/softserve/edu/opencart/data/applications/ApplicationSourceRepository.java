package com.softserve.edu.opencart.data.applications;

public final class ApplicationSourceRepository {

    private static volatile ApplicationSourceRepository instance = null;

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

    public IApplicationSource defaultSource() {
        return openCartChrome();
    }

    public IApplicationSource openCartChrome() {
        return ApplicationSource.get()
                .setBrowserName("ChromeTemporary")
                .setDriverPath("C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe")
                .setImplicitWaitTimeOut(10)
                .setBaseUrl("https://nazaronoc.000webhostapp.com/")
                .build();
    }


    public IApplicationSource openCartFirefox() {
        return ApplicationSource.get()
                .setBrowserName("FirefoxTemporary")
                .setDriverPath("C:/Program Files/Mozilla Firefox/geckodriver.exe")
                .setImplicitWaitTimeOut(10)
                .setBaseUrl("https://nazaronoc.000webhostapp.com/")
                .build();
    }


}
