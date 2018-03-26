package com.softserve.edu.opencart.tools;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.softserve.edu.opencart.pages.Application;
import com.softserve.edu.opencart.tests.TestRunner;

public class ScreenshotAtFailTestListener extends TestRunner implements ITestListener {
    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss";
    
    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            takeScreenShot(result.getName());
            System.out.println("Screen");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        
    }
    
    private void takeScreenShot(String testName) throws IOException {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./ScreensOnFail/"+testName+ "_" + currentTime + "_screenshot.png"));
    }
 
}
