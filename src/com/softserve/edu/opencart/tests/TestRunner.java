package com.softserve.edu.opencart.tests;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.softserve.edu.opencart.data.applications.ApplicationSourceRepository;
import com.softserve.edu.opencart.pages.Application;

public abstract class TestRunner {

    // Use, if class Application is not singleton
    // protected Application application;
    
    @BeforeClass
    public void beforeClass(ITestContext context) {
        System.out.println("@BeforeClass");
        // TODO Read context
        Application.get(ApplicationSourceRepository.EpizyChrome());
    }

    
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("@AfterClass");
        //
        Application.remove();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("@BeforeMethod");
    }

    @AfterMethod//(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        //Reporter.setCurrentTestResult(result);
        System.out.println("@AfterMethod");
    }

}
