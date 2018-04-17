package com.softserve.edu.opencart.data.applications;

import java.sql.Driver;

//*********Builder Pattern*********
interface IBrowserName {
    IDriverPath setBrowserName(String browserName);
}

interface IDriverPath {
    IBaseUrl setDriverPath(String driverPath);
}

/*interface IBaseUrl {
    IApplicationSourceBuild setBaseUrl(String baseUrl);
}*/

interface IBaseUrl {
    IImplicitWaitTimeOut setBaseUrl(String baseUrl);
}

interface IImplicitWaitTimeOut {
    IDatabaseUrl setImplicitWaitTimeOut(long implicitWaitTimeOut);
}

interface IDatabaseUrl {
    IDatabaseLogin setDatabaseUrl(String DatabaseUrl);
}

interface IDatabaseLogin {
    IDatabasePassword setDatabaseLogin(String DatabaseLogin);
}

interface IDatabasePassword {
    IJdbcDriver setDatabasePassword(String DatabasePassword);
}

interface IJdbcDriver {
    IApplicationSourceBuild setJdbcDriver(Driver JdbcDriver);
}

interface IApplicationSourceBuild {
    IApplicationSource build();
}

public final class ApplicationSource implements IBrowserName, IDriverPath,
                   IBaseUrl, IApplicationSourceBuild, IImplicitWaitTimeOut,
        IDatabaseUrl,IDatabaseLogin,IDatabasePassword,IJdbcDriver,
                                                        IApplicationSource {

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
    private String databaseUrl;
    private String databaseLogin;
    private String databasePassword;
    private Driver jdbcDriver;

    // *********Constructor*********
    private ApplicationSource() {
    }

    public static IBrowserName get() {
        return new ApplicationSource();
    }
    //*********Setters*********
    public IDriverPath setBrowserName(final String browserName) {
        this.browserName = browserName;
        return this;
    }

    public IBaseUrl setDriverPath(final String driverPath) {
        this.driverPath = driverPath;
        return this;
    }

    public IImplicitWaitTimeOut setBaseUrl(final String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public IDatabaseUrl setImplicitWaitTimeOut(final long implicitWaitTimeOut ) {
        this.implicitWaitTimeOut = implicitWaitTimeOut;
        return this;
    }
    public IDatabaseLogin setDatabaseUrl(final String databaseUrl) {
        this.databaseUrl = databaseUrl;
        return this;
    }
    public IDatabasePassword setDatabaseLogin(final String databaseLogin) {
        this.databaseLogin = databaseLogin;
        return this;
    }
    public IJdbcDriver setDatabasePassword(final String databasePassword) {
        this.databasePassword = databasePassword;
        return this;
    }

   /* public IApplicationSourceBuild setBaseUrl(final String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }*/

    public IApplicationSourceBuild setJdbcDriver(final Driver jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
        return this;
    }



    public IApplicationSource build() {
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