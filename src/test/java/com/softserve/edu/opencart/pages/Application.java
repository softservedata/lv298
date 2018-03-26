package com.softserve.edu.opencart.pages;

import com.softserve.edu.opencart.data.applications.ApplicationSourceRepository;
import com.softserve.edu.opencart.data.applications.IApplicationSource;
import com.softserve.edu.opencart.tools.BrowserWrapper;

public class Application {

    // Use Singleton, Repository

    private static volatile Application instance;

    // Fields

    private IApplicationSource applicationSource;
    private BrowserWrapper browser;

    private Application(IApplicationSource applicationSource) {
        this.applicationSource = applicationSource;
    }

    public static Application get() {
        return get(null);
    }

    public static Application get(IApplicationSource applicationSource) {
        if (instance == null) {
            synchronized (Application.class) {
                if (instance == null) {
                    if (applicationSource == null) {
                        applicationSource = ApplicationSourceRepository.get().openCartChrome();
                    }
                    instance = new Application(applicationSource);
                    instance.initBrowser(applicationSource);
                }
            }
        }
        return instance;
    }

    public static void remove() {
        if (instance != null) {
            instance.getBrowser().quit();
            instance = null;
        }
    }

    // getters
    public IApplicationSource getApplicationSource() {
        return applicationSource;
    }


    public BrowserWrapper getBrowser() {
        return browser;
    }

    private void initBrowser(IApplicationSource applicationSource) {
        this.browser = new BrowserWrapper(applicationSource);
    }
    // Pages

    public HomePage loadHomePage() {
        // TODO Remove getBrowser().getDriver()
        return HomePage.load(getBrowser().getDriver(), applicationSource.getBaseUrl());
    }


}