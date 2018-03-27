package com.softserve.edu.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.data.applications.ApplicationSourceRepository;
import com.softserve.edu.opencart.data.applications.IApplicationSource;
import com.softserve.edu.opencart.tools.BrowserWrapper;

public class Application {

    // Use Singleton, Repository
    private static volatile Application instance;
    //
    // Fields
    //
    // TODO Change for parallel work
    private IApplicationSource applicationSource;
    //private CaptureUtils captureUtils;
    //private ReporterWrapper reporter;
    //private FlexAssert flexAssert;
    private BrowserWrapper browser;
    //private ISearch search;
    //private ConnectionManager connectionManager;
    // etc.

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
                    //instance.initCaptureUtils();
                    //instance.initReporter(applicationSource);
                    //instance.initFlexAssert();
                    instance.initBrowser(applicationSource);
                    //instance.initSearch(applicationSource);
                    // initAccessToDB();
                    //instance.initConnectionManager(applicationSource);
                }
            }
        }
        return instance;
    }

    public static void remove() {       
        if (instance != null) {        
            // TODO Change for parallel work
            instance.getBrowser().quit();
            //instance.connectionManager().closeAllConnections();
            instance = null;
        }
    }
    // TODO legal?
    public  WebDriver driver() {           
             return instance.getBrowser().getDriver();
    }

    // getters

    // TODO Change for parallel work
    // TODO remove get
    public IApplicationSource getApplicationSource() {
        return applicationSource;
    }

//    public CaptureUtils captureUtils() {
//        return captureUtils;
//    }

//    public ReporterWrapper reporter() {
//        return reporter;
//    }

//    public FlexAssert flexAssert() {
//        return flexAssert;
//    }

    public BrowserWrapper getBrowser() {
        return browser;
    }

//    public ISearch search() {
//        return search;
//    }

//    public ConnectionManager connectionManager() {
//        return connectionManager;
//    }

    // Initialization

    // TODO Change for parallel work
//    private void initCaptureUtils() {
//        // TODO Add parameters to applicationSource
//        this.captureUtils = new CaptureUtils();
//    }

//    private void initReporter(IApplicationSource applicationSource) {
//        this.reporter = new ReporterWrapper(applicationSource);
//    }

//    private void initFlexAssert() {
//        this.flexAssert = new FlexAssert(reporter());
//    }

    private void initBrowser(IApplicationSource applicationSource) {
        this.browser = new BrowserWrapper(applicationSource);
    }

//    private void initSearch(IApplicationSource applicationSource) {
//        this.search = new Search(applicationSource);
//    }

//    private void initConnectionManager(IApplicationSource applicationSource) {
//        this.connectionManager = new ConnectionManager(applicationSource);
//    }

    // Pages

    public HomePage loadHomePage() {
        //getBrowser().openUrl(applicationSource.getBaseUrl());
        // TODO Remove getBrowser().getDriver()
        // return new HomePage(browser().getDriver());
        //return new HomePage();
        return HomePage.load(getBrowser().getDriver(), applicationSource.getBaseUrl());
    }
    
    public HomePage refreshHomePage() {
        //getBrowser().openUrl(applicationSource.getBaseUrl());
        // TODO Remove getBrowser().getDriver()
        // return new HomePage(browser().getDriver());
        //return new HomePage();
        return HomePage.refresh(getBrowser().getDriver());
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
