package com.softserve.edu.opencart.data.applications;

//*********Builder Pattern*********
interface IBrowserName {
    IDriverPath setBrowserName(String browserName);
}

interface IDriverPath {
    IImplicitWaitTimeOut setDriverPath(String driverPath);
}

interface IImplicitWaitTimeOut {
    IBaseUrl setImplicitWaitTimeOut(long implicitWaitTimeOut);
}

interface IBaseUrl {
    IApplicationSourceBuild setBaseUrl(String baseUrl);
}

interface IApplicationSourceBuild {
    IApplicationSource build();
}

public class ApplicationSource implements IBrowserName, IDriverPath, IImplicitWaitTimeOut,
                                        IBaseUrl,IApplicationSourceBuild,IApplicationSource {

    //*********Browser Data*********
    private String browserName;
    private String driverPath;

    // private String browserPath;
    // private String defaulProfile;
    //
    //*********Implicit and Explicit Waits*********
    private long implicitWaitTimeOut;
    //private long implicitLoadTimeOut;
    //private long implicitScriptTimeOut;
    //private long explicitTimeOut;
    //
    //*********Localization Strategy*********
    // private String language;
    //
    //*********Search Strategy*********
    //private String searchStrategy;
    //
    //*********Logger Strategy*********
    // private String loggerStrategy;
    //
    //*********Reporter Console Output*********
    //private boolean consoleOutput;
    //
    //*********URLs*********
    private String baseUrl;
    //private String userLoginUrl;
    //private String userLogoutUrl;
    //
    //private String adminLoginUrl;
    //private String adminLogoutUrl;
    //
    //*********Database Connection*********
    //private String databaseUrl;
    //private String databaseLogin;
    //private String databasePassword;

    // *********Constructor*********
    private ApplicationSource() {       
    }
    
    public static IBrowserName get () {
        return new ApplicationSource();
    }
    
    //*********Setters********* 
    public IDriverPath setBrowserName(String browserName) {
        this.browserName = browserName;
        return this;
    }

    public IImplicitWaitTimeOut setDriverPath(String driverPath) {
        this.driverPath = driverPath;
        return this;
    }

    public IBaseUrl setImplicitWaitTimeOut(long implicitWaitTimeOut) {
        this.implicitWaitTimeOut = implicitWaitTimeOut;
        return this;
    }

    public IApplicationSourceBuild setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
    
    public IApplicationSource build () {
        return this;
    }

    //*********Getters********* 
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
