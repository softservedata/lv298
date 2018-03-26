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

    @BeforeClass
    public void beforeClass(ITestContext context) {
        System.out.println("@BeforeClass");
        Application.get(ApplicationSourceRepository.get().openCartChrome());
    }

    
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("@AfterClass");
        Application.remove();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("@BeforeMethod");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        System.out.println("@AfterMethod");
    }

}
