package com.softserve.edu.opencart.data.applications;

public interface IApplicationSource {

    String getBrowserName();

    String getDriverPath();

    long getImplicitWaitTimeOut();

    String getBaseUrl();

}
