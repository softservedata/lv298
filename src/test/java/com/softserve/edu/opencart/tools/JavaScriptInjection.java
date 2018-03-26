package com.softserve.edu.opencart.tools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptInjection {
    private final String SCROOL_TO_ELEMENT = "arguments[0].scrollIntoView();";

    private WebDriver driver;
    private WebElement element;

    private JavaScriptInjection() {
    }

    public static JavaScriptInjection inject() {
        return new JavaScriptInjection();
    }

    public void scroll(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(SCROOL_TO_ELEMENT, element);
    }
}
