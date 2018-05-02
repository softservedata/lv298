package com.softserve.edu.opencart.data.applications;

import java.sql.Driver;

public class ApplicationSource implements IApplicationSource {

    // Browser Data
    private String browserName;
    private String driverPath;
    private long implicitWaitTimeOut;
    private String baseUrl;
    private String databaseUrl;
    private String databaseLogin;
    private String databasePassword;
    private Driver jdbcDriver;

    public ApplicationSource(String browserName, String driverPath,
                             long implicitWaitTimeOut, String baseUrl,
                             String databaseUrl, String databaseLogin,
                             String databasePassword, Driver jdbcDriver) {
        this.browserName = browserName;
        this.driverPath = driverPath;
        this.implicitWaitTimeOut = implicitWaitTimeOut;
        this.baseUrl = baseUrl;
        this.databaseUrl = databaseUrl;
        this.databaseLogin = databaseLogin;
        this.databasePassword = databasePassword;
        this.jdbcDriver = jdbcDriver;

    }

    //Setters

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public void setImplicitWaitTimeOut(long implicitWaitTimeOut) {
        this.implicitWaitTimeOut = implicitWaitTimeOut;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public void setDatabaseLogin(String databaseLogin) {
        this.databaseLogin = databaseLogin;
    }

    public void setDatabasePassword() {
        this.databasePassword = databasePassword;
    }

    public void setJdbcDriver() {
        setJdbcDriver();
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

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabaseLogin() {
        return databaseLogin;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public Driver getJdbcDriver() {
        return jdbcDriver;
    }

}
