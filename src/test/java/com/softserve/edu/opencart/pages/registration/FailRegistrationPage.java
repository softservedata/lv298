package com.softserve.edu.opencart.pages.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FailRegistrationPage extends RegistrationPage{

    public FailRegistrationPage(WebDriver driver) {
	super(driver);
	this.driver = driver;
    }

    public WebElement getErrorMessage() {
	return driver.findElement(By.cssSelector(".alert.alert-danger"));
    }
    
    public String getErrorText() {
	return getErrorMessage().getText();
    }
}
