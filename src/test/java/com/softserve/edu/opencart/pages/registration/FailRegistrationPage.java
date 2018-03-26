package com.softserve.edu.opencart.pages.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FailRegistrationPage extends RegistrationPage {
    private final String ALERT_ELEMENT_BY_CSS_SELECTOR = ".alert.alert-danger";
    public static final String WRONG_EMAIL_MESSAGE = "Warning: E-Mail Address is already registered!";

    public FailRegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement getErrorMessage() {
        return driver.findElement(By.cssSelector(ALERT_ELEMENT_BY_CSS_SELECTOR));
    }

    public String getErrorText() {
        return getErrorMessage().getText();
    }
}
