package com.softserve.edu.opencart.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.softserve.edu.opencart.data.applications.ApplicationSourceRepository;
import com.softserve.edu.opencart.pages.Application;

public abstract class TestRunner {
    public static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

    // Use, if class Application is not singleton
    // protected Application application;
    
    @BeforeClass
    public void beforeClass(ITestContext context) {
        logger.info("@BeforeClass start");
        //System.out.println("@BeforeClass");
        // TODO Read context
        Application.get(ApplicationSourceRepository.EpizyChrome());
        logger.info("@BeforeClass done");
    }

    
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        logger.info("@AfterClass start");
        //System.out.println("@AfterClass");
        //
        Application.remove();
        logger.info("@AfterClass done");
    }

    @BeforeMethod
    public void beforeMethod() {
        logger.info("@BeforeMethod start");
        //System.out.println("@BeforeMethod");
        logger.info("@BeforeMethod done");
    }

    @AfterMethod//(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        logger.info("@AfterMethod start");
        //Reporter.setCurrentTestResult(result);
        //System.out.println("@AfterMethod");
        logger.info("@AfterMethod done");
    }

}
