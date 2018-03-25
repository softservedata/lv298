package com.softserve.edu.opencart.data.applications;

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
    IApplicationSourceBuilder setBaseUrl(String baseUrl);
}

interface IApplicationSourceBuilder {
    IApplicationSource build();
}


public class ApplicationSource implements   IBrowserName, IDriverPath, IImplicitWaitTimeOut, IBaseUrl,
                                            IApplicationSourceBuilder, IApplicationSource {

    // Browser Data
    private String browserName;
    private String driverPath;
    private long implicitWaitTimeOut;
    private String baseUrl;

    // TODO Develop Builder
    private ApplicationSource() {
    }

    public static IBrowserName get() {
        return new ApplicationSource();
    }

    //Setters
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

    public IApplicationSourceBuilder setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public IApplicationSource build() {
        return this;
    }

    //GETTERS
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
