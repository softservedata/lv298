package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.data.applications.ApplicationSourceRepository;
import com.softserve.edu.opencart.pages.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import org.testng.internal.thread.ThreadTimeoutException;

public abstract class TestRunner {
    final private String NEW_LINE_SYMBHOL = "\n";
    final private String TAB_SYMBHOL = "\t";


    public static final Logger logger = LoggerFactory.getLogger(TestRunner.class);


    @BeforeSuite
    public void beforeClass(ITestContext context) {
        logger.debug("Before Suite TestNG");
        Application.get(ApplicationSourceRepository.get().openCartWithoutUIChrome());
    }


    @AfterSuite(alwaysRun = true)
    public void afterClass() {
        logger.debug("After Suite TestNG");
        Application.remove();
    }

    @BeforeMethod
    public void beforeMethod() {

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) {
        String message = NEW_LINE_SYMBHOL;
        if (testResult.getStatus() == ITestResult.SUCCESS) {
            message = message + "PASSED:" + testResult.getName()
                    + "TIME: " + (testResult.getEndMillis() - testResult.getStartMillis());
        } else {
            message = message + "FAILED: " + testResult.getName();
            Throwable throwable = testResult.getThrowable();
            if (throwable != null) {
                message = message + NEW_LINE_SYMBHOL + throwable.toString();
                if (!(throwable instanceof ThreadTimeoutException)) {
                    for (StackTraceElement e : throwable.getStackTrace()) {
                        message = message + NEW_LINE_SYMBHOL + TAB_SYMBHOL + e.toString();
                    }
                }
            }
        }
        logger.debug(message);
    }
}
