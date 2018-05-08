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
import org.testng.internal.thread.ThreadTimeoutException;

public abstract class TestRunner {
    // Use, if class Application is not singleton
    // protected Application application;

    public static final Logger logger = LoggerFactory.getLogger(TestRunner.class);
    
    @BeforeClass
    public void beforeClass(ITestContext context) {
        logger.info("@BeforeClass start");
       // System.out.println("@BeforeClass");
        // TODO Read context
        Application.get(ApplicationSourceRepository.epizyChrome());
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

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) {
        logger.info("@AfterMethod start");
        //Reporter.setCurrentTestResult(result);
        //System.out.println("@AfterMethod");
        //
        String message = "\n";
        if (testResult.getStatus() == ITestResult.SUCCESS) {
            message = message + "PASSED: " + testResult.getName();
        } else {
            message = message + "FAILED: " + testResult.getName();
            Throwable throwable = testResult.getThrowable();
            if (throwable != null) {
                message = message + "\n" + throwable.toString();
                //
                // If it's not a thread timeout, include the stack trace too
                if (!(throwable instanceof ThreadTimeoutException)) {
                    for (StackTraceElement e : throwable.getStackTrace()) {
                        message = message + "\n        at " + e.toString();
                    }
                }
            }
        }
        //
        logger.info(message);
        logger.info("@AfterMethod done");
    }

}
