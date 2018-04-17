package com.softserve.edu.opencart.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.softserve.edu.opencart.pages.Application;

public class ScreenshotAtFailTestListener implements ITestListener {
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
            //saveScreenshotPNG();
            System.out.println("Screenshot");
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
        JavascriptExecutor executor = (JavascriptExecutor)Application.get().driver();

        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        executor.executeScript("document.body.style.zoom = '0.5'");
        File scrFile = ((TakesScreenshot)Application.get().driver()).getScreenshotAs(OutputType.FILE);
        saveScreenshotPNG();
        FileUtils.copyFile(scrFile, new File("./ScreensOnFail/"+testName+ "_" + currentTime + "_screenshot.png"));
        executor.executeScript("document.body.style.zoom = '1'");
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(){
        return ((TakesScreenshot)Application.get().driver()).getScreenshotAs(OutputType.BYTES);
    }
 
}
