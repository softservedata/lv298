package com.softserve.edu;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AppTest {
    public static final Logger logger = LoggerFactory.getLogger(AppTest.class);
    private boolean isTestComplete;
    private SoftAssert softAssert;

    //@Test
    public void testApp1() throws Exception {
        System.out.println("surefire.reports.directory = "
                + System.getProperty("surefire.reports.directory"));
        System.out.println("selenium-version = "
                + System.getProperty("selenium-version"));
        System.out.println("database-password = "
                + System.getProperty("database-password"));
        //
        System.out.println("System.getenv() = "
                + System.getenv());
    }

    @BeforeMethod
    public void setUp() throws Exception {
        System.out.println("CalcTest @Before setUp()");
        isTestComplete = false;
    }

    @AfterMethod
    //public void tearDown() throws Exception {
    public void tearDown(ITestResult result) throws Exception {
        Reporter.setCurrentTestResult(result);
        System.out.println("CalcTest @After tearDown()");
        Reporter.log("<BR><FONT SIZE='4' COLOR='RED'>@AfterMethod Non Conditional.</FONT><BR>", true);
        Reporter.log("<BR><FONT SIZE='4' COLOR='BLUE'>@AfterMethod Level 3</FONT><BR>", 3, true);
        Reporter.log("<BR><FONT SIZE='4' COLOR='BLUE'>@AfterMethod Level 5</FONT><BR>", 5);
        Reporter.log("<BR><FONT SIZE='4' COLOR='BLUE'>@AfterMethod Level 7</FONT><BR>", 7, true);
        if (!isTestComplete) {
            Reporter.log("<BR><BR><FONT SIZE='4' COLOR='BLUE'>ALARM TEST FAIL</FONT><BR>", true);
            // Add Screenshot, etc. 
        }
    }

    @BeforeClass
    public void beforeClass(ITestContext context) {
        logger.info("@BeforeClass for " + this.getClass().getName());
        //
        softAssert = new SoftAssert();
        //
        HashMap<String, String> allParameters = new HashMap<String, String>(
                context.getCurrentXmlTest().getAllParameters());
        for (String key : allParameters.keySet()) {
            System.out.println("*** parameter: key=" + key + " value=" + allParameters.get(key));
        }
        System.out.println("@BeforeClass");
    }
    
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        logger.info("@AfterClass for " + this.getClass().getName());
    }
    
    //@Test
    public void testApp2() {
        Reporter.log("<BR><FONT SIZE='4' COLOR='RED'>Non Conditional.</FONT><BR>", true);
        Reporter.log("<BR><FONT SIZE='4' COLOR='BLUE'>Level 3</FONT><BR>", 3, true);
        Reporter.log("<BR><FONT SIZE='4' COLOR='BLUE'>Level 5</FONT><BR>", 5);
        Reporter.log("<BR><FONT SIZE='4' COLOR='BLUE'>Level 7</FONT><BR>", 7, true);
        System.out.println("Running . . .");
        // ReporterWrapper.get().debug("DEBUG - messages");
        // ReporterWrapper.get().info("INFO - messages");
        // ReporterWrapper.get().warning("WARNING - messages");
        // ReporterWrapper.get().error("ERROR - messages");
        // Assert.assertTrue(false);
        Assert.assertTrue(true);
        isTestComplete = true;
    }

    @Test
    public void testApp3() {
        logger.info("\t+++testApp3() DONE");
        Reporter.log("\t+++testApp3() must be", true);
        Reporter.log("\t+++testApp3() 3", 3, true);
        Reporter.log("\t+++testApp3() 5", 5, true);
        Reporter.log("\t+++testApp3() 7", 7, true);
        Reporter.log("\t+++testApp3() 9", 9, true);
        //
        System.out.println("\t+++testApp3() Assert.assertEquals(1+2, 4) ...");
        //Assert.assertEquals(1 + 2, 4);
        //softAssert.assertEquals(1 + 2, 4);
        softAssert.assertEquals(1 + 2, 3);
        //
        System.out.println("\t+++testApp3() Assert.assertEquals(2 + 2, 5) ...");
        //Assert.assertEquals(2 + 2, 5);
        //softAssert.assertEquals(2 + 2, 5);
        softAssert.assertEquals(2 + 2, 4);
        //
        System.out.println("\t+++testApp3() softAssert.assertAll() ...");
        softAssert.assertAll();
        //
        System.out.println("\t+++testApp3() DONE");
        isTestComplete = true;
    }

    @Test(dependsOnMethods = { "testApp3" })
    public void testApp4() {
        System.out.println("\t***testApp4() softAssert.assertAll() ...");
        softAssert.assertAll();
        System.out.println("\t***testApp4() DONE");
        isTestComplete = true;
    }
}