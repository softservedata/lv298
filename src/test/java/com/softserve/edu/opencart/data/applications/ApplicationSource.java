package com.softserve.edu.opencart.data.applications;

public class ApplicationSource implements IApplicationSource {

    // Browser Data
    private String browserName;
    private String driverPath;

    private long implicitWaitTimeOut;
    private String baseUrl;

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
