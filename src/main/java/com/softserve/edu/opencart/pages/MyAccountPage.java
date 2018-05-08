package com.softserve.edu.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends ARightPanel {
    public static final String MY_ACCOUNT_LABEL_TEXT = "My Account";

    // *********Web Elements*********
    @FindBy(css = "#content > h2:first-child")
    private WebElement myAccountLabel;

    // *********Constructor*********
    public MyAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // *********My Account Label*********
    public WebElement getMyAccountLabel() {
        return myAccountLabel;
    }

    public String getMyAccountLabelText() {
        return getWebElementText(getMyAccountLabel());
    }

}
