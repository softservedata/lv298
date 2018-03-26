package com.softserve.edu.opencart.data.applications;

public class ApplicationSource implements IApplicationSource {

    // Browser Data
    private String browserName;
    private String driverPath;

    // private String browserPath;
    // private String defaulProfile;
    //
    // Implicit and Explicit Waits
    private long implicitWaitTimeOut;
    //private long implicitLoadTimeOut;
    //private long implicitScriptTimeOut;
    //private long explicitTimeOut;
    //
    // Localization Strategy
    // private String language;
    //
    // Search Strategy
    //private String searchStrategy;
    //
    // Logger Strategy
    // private String loggerStrategy;
    //
    // Reporter Console Output
    //private boolean consoleOutput;
    //
    // URLs
    private String baseUrl;
    //private String userLoginUrl;
    //private String userLogoutUrl;
    //
    //private String adminLoginUrl;
    //private String adminLogoutUrl;
    //
    // Database Connection
    //private String databaseUrl;
    //private String databaseLogin;
    //private String databasePassword;

    // TODO Develop Builder
    public ApplicationSource(String browserName, String driverPath,
            long implicitWaitTimeOut, String baseUrl) {
        this.browserName = browserName;
        this.driverPath = driverPath;
        this.implicitWaitTimeOut = implicitWaitTimeOut;
        this.baseUrl = baseUrl;
    }
    
    // setters
    
    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public void setImplicitWaitTimeOut(long implicitWaitTimeOut) {
        this.implicitWaitTimeOut = implicitWaitTimeOut;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // getters

    public String getBrowserName() {
        return browserName;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public long getImplicitWaitTimeOut() {
        return implicitWaitTimeOut;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

}
