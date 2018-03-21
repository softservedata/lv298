package com.softserve.edu.opencart.pages;

import com.softserve.edu.opencart.data.applications.ApplicationSourceRepository;
import com.softserve.edu.opencart.data.applications.IApplicationSource;
import com.softserve.edu.opencart.tools.BrowserWrapper;

public class Application {
    private static volatile Application instance;

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
			applicationSource = ApplicationSourceRepository.defaultParameters();
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
	    // TODO Change for parallel work
	    instance.getBrowser().quit();
	    // instance.connectionManager().closeAllConnections();
	    instance = null;
	}
    }

    // getters

    // TODO Change for parallel work
    // TODO remove get
    public IApplicationSource getApplicationSource() {
	return applicationSource;
    }

    // public CaptureUtils captureUtils() {
    // return captureUtils;
    // }

    // public ReporterWrapper reporter() {
    // return reporter;
    // }

    // public FlexAssert flexAssert() {
    // return flexAssert;
    // }

    public BrowserWrapper getBrowser() {
	return browser;
    }

    // public ISearch search() {
    // return search;
    // }

    // public ConnectionManager connectionManager() {
    // return connectionManager;
    // }

    // Initialization

    // TODO Change for parallel work
    // private void initCaptureUtils() {
    // // TODO Add parameters to applicationSource
    // this.captureUtils = new CaptureUtils();
    // }

    // private void initReporter(IApplicationSource applicationSource) {
    // this.reporter = new ReporterWrapper(applicationSource);
    // }

    // private void initFlexAssert() {
    // this.flexAssert = new FlexAssert(reporter());
    // }

    private void initBrowser(IApplicationSource applicationSource) {
	this.browser = new BrowserWrapper(applicationSource);
    }

    // private void initSearch(IApplicationSource applicationSource) {
    // this.search = new Search(applicationSource);
    // }

    // private void initConnectionManager(IApplicationSource applicationSource) {
    // this.connectionManager = new ConnectionManager(applicationSource);
    // }

    // Pages

    public HomePage loadHomePage() {
	return HomePage.load(getBrowser().getDriver(), applicationSource.getBaseUrl());
    }
    

    // public LoginPage login() {
    // getBrowser().openUrl(applicationSource.getUserLoginUrl());
    // return new LoginPage();
    // }

    // public LogoutPage logout() {
    // getBrowser().openUrl(applicationSource.getUserLogoutUrl());
    // return new LogoutPage();
    // }

}
