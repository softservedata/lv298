package com.softserve.edu.opencart.data.applications;

import java.sql.Driver;

public interface IApplicationSource {

    String getBrowserName();

    String getDriverPath();

    long getImplicitWaitTimeOut();

    String getBaseUrl();

    String getDatabaseUrl();

    String getDatabaseLogin();

    String getDatabasePassword();

    Driver getJdbcDriver();

}