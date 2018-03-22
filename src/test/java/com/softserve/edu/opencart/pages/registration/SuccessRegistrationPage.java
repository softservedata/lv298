package com.softserve.edu.opencart.pages.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SuccessRegistrationPage extends RegistrationPage {

    public SuccessRegistrationPage(WebDriver driver) {
	super(driver);
	this.driver = driver;
    }
    
    public WebElement getSuccessfullRegistrationElement() {
	return driver.findElement(By.tagName("h1"));
    }
    
    public String getSuccessfullRegistrationText() {
	return getSuccessfullRegistrationElement().getText();
    }
}
