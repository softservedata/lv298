package com.softserve.edu.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.softserve.edu.opencart.data.users.IUser;

public class LoginPage extends ARightPanel {

    // *********Web Elements*********
    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(css = "input.btn.btn-primary")
    private WebElement loginButton;

    // *********Constructor*********
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // *********Email Field*********
    public WebElement getEmailField() {
        return emailField;
    }
    
    public String getEmailFieldText() {
        return getWebElementTextWithAttribute(getEmailField(), ATTRIBUTE_VALUE);
    }

    public void sendEmailFieldText(String text) {
        sendWebElementText(getEmailField(), text);
    }

    public void clearEmailField() {
        clearWebElement(getEmailField());
    }

    public void clickEmailField() {
        clickWebElement(getEmailField());
    }

    // *********Password Field*********
    public WebElement getPasswordField() {
        return passwordField;
    }
    
    public String getPasswordFieldText() {
        return getWebElementTextWithAttribute(getPasswordField(), ATTRIBUTE_VALUE);
    }

    public void sendPasswordFieldText(String text) {
        sendWebElementText(getPasswordField(), text);
    }

    public void clearPasswordField() {
        clearWebElement(getPasswordField());
    }

    public void clickPasswordField() {
        clickWebElement(getPasswordField());
    }

    // *********Login Button********* 
    public WebElement getLoginButton() {
        return loginButton;
    }
    
    public String getLoginButtonText() {
        return getWebElementTextWithAttribute(getLoginButton(), ATTRIBUTE_VALUE);
    }
    
    public void clickLoginButton() {
        clickWebElement(getLoginButton());
    }

    // *********Business Logic*********

    private void fillLoginForm(IUser user) {
        clickEmailField();
        clearEmailField(); 
        sendEmailFieldText(user.getEmail());
      
        clickPasswordField();
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
