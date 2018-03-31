package com.softserve.edu.opencart.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.softserve.edu.opencart.data.applications.ApplicationSourceRepository;
import com.softserve.edu.opencart.pages.Application;
import org.testng.asserts.SoftAssert;

public abstract class TestRunner {
    public static final Logger logger = LoggerFactory.getLogger(TestRunner.class);
    protected SoftAssert softAssert;


    @BeforeClass
    public void beforeClass(ITestContext context) {
        logger.debug("beforeClass TestNG");
        Application.get(ApplicationSourceRepository.get().openCartWithoutUIChrome());
    }

    
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        logger.debug("afterClass TestNG");
        Application.remove();
    }

    @BeforeMethod
    public void beforeMethod() {
        logger.debug("beforeMethod TestNG");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        logger.debug("afterMethod TestNG");

    }

}
