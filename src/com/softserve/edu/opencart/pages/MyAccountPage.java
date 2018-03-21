package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage extends ARightPanel {
    public static final String MY_ACCOUNT_LABEL_TEXT = "My Account";

    private WebElement myAccountLabel;
    
    public MyAccountPage(WebDriver driver) {
        super(driver);
        myAccountLabel = driver.findElement(By.cssSelector("#content > h2:first-child"));
    }
    
    // myAccountLabel
    public WebElement getMyAccountLabel() {
        return myAccountLabel;
    }

    public String getMyAccountLabelText() {
        return getMyAccountLabel().getText();
    }

}
