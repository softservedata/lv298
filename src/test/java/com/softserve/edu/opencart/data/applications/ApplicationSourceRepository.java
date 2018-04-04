package com.softserve.edu.opencart.data.applications;

import com.softserve.edu.opencart.tools.BrowserWrapper;

public final class ApplicationSourceRepository {
    final String BASE_URL = "https://nazaronoc.000webhostapp.com/";
    final String CHROME_WINDOWS_DRIVER_RELATIVE_PATH = "/chromedriver-windows-32bit.exe";
    final String FIREFOX_WINDOWS_DRIVER_RELATIVE_PATH ="/geckodriver-windows-64bit.exe";

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
                .setDriverPath(ApplicationSourceRepository.class.getResource(CHROME_WINDOWS_DRIVER_RELATIVE_PATH)
                        .getPath()
                        .substring(1))
                .setImplicitWaitTimeOut(10)
                .setBaseUrl(BASE_URL)
                .build();
    }


    public IApplicationSource openCartFirefox() {
        return ApplicationSource.get()
                .setBrowserName("FirefoxTemporary")
                .setDriverPath(ApplicationSourceRepository.class.getResource(FIREFOX_WINDOWS_DRIVER_RELATIVE_PATH)
                        .getPath()
                        .substring(1))
                .setImplicitWaitTimeOut(10)
                .setBaseUrl(BASE_URL)
                .build();
    }

    public IApplicationSource openCartWithoutUIChrome(){
        return ApplicationSource.get()
                .setBrowserName("ChromeWithoutUI")
                .setDriverPath(ApplicationSourceRepository.class.getResource(CHROME_WINDOWS_DRIVER_RELATIVE_PATH)
                        .getPath()
                        .substring(1))
                .setImplicitWaitTimeOut(10)
                .setBaseUrl(BASE_URL)
                .build();
    }


}
