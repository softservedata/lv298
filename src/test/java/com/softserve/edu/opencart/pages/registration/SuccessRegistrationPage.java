package com.softserve.edu.opencart.pages.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SuccessRegistrationPage extends RegistrationPage {
    private final String HEADER_ELEMENT_BY_TAG = "h1";
    public static final String SUCCSESS_REGISTRATION_MESSAGE = "Your Account Has Been Created!";

    public SuccessRegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement getSuccessfullRegistrationElement() {
        return driver.findElement(By.tagName(HEADER_ELEMENT_BY_TAG));
    }

    public String getSuccessfullRegistrationText() {
        return getSuccessfullRegistrationElement().getText();
    }
}
