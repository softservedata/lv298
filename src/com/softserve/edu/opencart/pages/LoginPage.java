package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.users.IUser;

public class LoginPage extends ARightPanel {

    private WebElement emailField;
    private WebElement passwordField;
    private WebElement loginButton;
    
    public LoginPage(WebDriver driver) {
        super(driver);
        emailField = driver.findElement(By.id("input-email"));
        passwordField = driver.findElement(By.id("input-password"));
        loginButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
    }

    // emailField
    public WebElement getEmailField() {
        return emailField;
    }

    public String getEmailFieldText() {
        return getEmailField().getAttribute(ATTRIBUTE_VALUE);
    }

    public void sendEmailFieldText(String text) {
        getEmailField().sendKeys(text);
    }

    public void clearEmailField() {
        getEmailField().clear();
    }

    public void clickEmailField() {
        getEmailField().click();
    }

    // passwordField
    public WebElement getPasswordField() {
        return passwordField;
    }

    public String getPasswordFieldText() {
        return getPasswordField().getAttribute(ATTRIBUTE_VALUE);
    }

    public void sendPasswordFieldText(String text) {
        getPasswordField().sendKeys(text);
    }

    public void clearPasswordField() {
        getPasswordField().clear();
    }

    public void clickPasswordField() {
        getPasswordField().click();
    }

    // loginButton
    public WebElement getLoginButton() {
        return loginButton;
    }

    public String getLoginButtonText() {
        return getLoginButton().getAttribute(ATTRIBUTE_VALUE);
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }

    // Business Logic
    
    private void fillLoginForm(IUser user) {
        clickEmailField();
        clearEmailField();
        sendEmailFieldText(user.getEmail());
        clickPasswordField();;
        clearPasswordField();
        sendPasswordFieldText(user.getPassword());
        clickLoginButton();
    }
    
    public MyAccountPage successLogin(IUser user) {
        fillLoginForm(user);
        return new MyAccountPage(driver);
    }

    public LoginFailPage unsuccessfullLogin(IUser invalidUser) {
        fillLoginForm(invalidUser);
        return new LoginFailPage(driver);
    }

    
}
