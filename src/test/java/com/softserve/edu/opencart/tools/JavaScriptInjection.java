package com.softserve.edu.opencart.tools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptInjection {
    WebDriver driver;
    
    WebElement element;

    private JavaScriptInjection() {
    }

    public static JavaScriptInjection inject() {
	return new JavaScriptInjection();
    }

    public void scroll(WebDriver driver, WebElement element) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView();", element);	
    }
}
