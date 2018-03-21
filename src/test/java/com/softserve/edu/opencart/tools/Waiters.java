package com.softserve.edu.opencart.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {
    private static final long TIME = 5;
    private WebDriverWait wait;
    private Waiters() {

    }

    public static Waiters ExplicitWait() {
	return new Waiters();
    }

    public void waitUntillSelected(WebDriver driver, WebElement element) {
	wait = new WebDriverWait(driver, TIME);
	wait.until(ExpectedConditions.elementToBeSelected(element));
	
    }
}
