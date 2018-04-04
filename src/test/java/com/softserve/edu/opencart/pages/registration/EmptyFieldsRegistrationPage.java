package com.softserve.edu.opencart.pages.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmptyFieldsRegistrationPage extends RegistrationPage {
    private final String FIRST_NAME_WRONG_MESSAGE_LOCATOR = "input#input-firstname + .text-danger";
    private final String LAST_NAME_WRONG_MESSAGE_LOCATOR = "input#input-lastname + .text-danger";
    private final String EMAIL_WRONG_MESSAGE_LOCATOR = "input#input-email + .text-danger";
    private final String TELEPHONE_WRONG_MESSAGE_LOCATOR = "input#input-telephone + .text-danger";
    private final String MAIN_ADDRESS_WRONG_MESSAGE_LOCATOR = "input#input-address-1 + .text-danger";
    private final String CITY_WRONG_MESSAGE_LOCATOR = "input#input-city + .text-danger";
    private final String POSTCODE_WRONG_MESSAGE_LOCATOR = "input#input-postcode + .text-danger";
    private final String STATE_WRONG_MESSAGE_LOCATOR = "select#input-zone + .text-danger";
    private final String PASSWORD_WRONG_MESSAGE_LOCATOR = "input#input-password + .text-danger";


    public final String WRONG_FIRST_NAME_MESSAGE = "First Name must be between 1 and 32 characters!";
    public final String WRONG_LAST_NAME_MESSAGE = "Last Name must be between 1 and 32 characters!";
    public final String WRONG_EMAIL_MESSAGE = "E-Mail Address does not appear to be valid!";
    public final String WRONG_TELEPHONE_MESSAGE = "Telephone must be between 3 and 32 characters!";
    public final String WRONG_MAIN_ADDRESS_MESSAGE = "Address 1 must be between 3 and 128 characters!";
    public final String WRONG_CITY_MESSAGE = "City must be between 2 and 128 characters!";
    public final String WRONG_POSTCODE_MESSAGE = "Postcode must be between 2 and 10 characters!";
    public final String WRONG_STATE_MESSAGE = "Please select a region / state!";
    public final String WRONG_PASSWORD_MESSAGE = "Password must be between 4 and 20 characters!";


    public EmptyFieldsRegistrationPage(WebDriver driver) {
        super(driver);
    }

    //First Name Wrong Message
    private WebElement getWrongFirstNameMessage() {
        return driver.findElement(By.cssSelector(FIRST_NAME_WRONG_MESSAGE_LOCATOR));
    }

    public String getWrongFirsNameMessageText() {
        scrollToFirstNameField();
        return getWrongFirstNameMessage().getText();
    }

    //Last Name Wrong Message
    private WebElement getWrongLastNameMessage() {
        return driver.findElement(By.cssSelector(LAST_NAME_WRONG_MESSAGE_LOCATOR));
    }

    public String getWrongLastNameMessageText() {
        scrollToLastNameField();
        return getWrongLastNameMessage().getText();
    }

    //E-mail Wrong Message
    private WebElement getWrongEmailMessage() {
        return driver.findElement(By.cssSelector(EMAIL_WRONG_MESSAGE_LOCATOR));
    }

    public String getWrongEmailMessageText() {
        scrollToEmailField();
        return getWrongEmailMessage().getText();
    }

    //Telephone Wrong Message
    private WebElement getWrongTelephoneMessage() {
        return driver.findElement(By.cssSelector(TELEPHONE_WRONG_MESSAGE_LOCATOR));
    }

    public String getWrongTelephoneMessageText() {
        scrollToTelephoneField();
        return getWrongTelephoneMessage().getText();
    }

    //Main address Wrong Message
    private WebElement getWrongMainAddressMessage() {
        return driver.findElement(By.cssSelector(MAIN_ADDRESS_WRONG_MESSAGE_LOCATOR));
    }

    public String getWrongMainAddressMessageText() {
        scrollToMainAddressField();
        return getWrongMainAddressMessage().getText();
    }

    //City Wrong Message
    private WebElement getWrongCityMessage() {
        return driver.findElement(By.cssSelector(CITY_WRONG_MESSAGE_LOCATOR));
    }

    public String getWrongCityMessageText() {
        scrollToCityField();
        return getWrongCityMessage().getText();
    }

    //Postcode Wrong Message
    private WebElement getWrongPostcodeMessage() {
        return driver.findElement(By.cssSelector(POSTCODE_WRONG_MESSAGE_LOCATOR));
    }

    public String getWrongPostcodeMessageText() {
        scrollToPostcodeField();
        return getWrongPostcodeMessage().getText();
    }

    //State Wrong Message
    private WebElement getWrongStateMessage() {
        return driver.findElement(By.cssSelector(STATE_WRONG_MESSAGE_LOCATOR));
    }

    public String getWrongStateMessageText() {
        scrollToStateSelector();
        return getWrongStateMessage().getText();
    }

    //Password Wrong Message
    private WebElement getWrongPasswrodMessage() {
        return driver.findElement(By.cssSelector(PASSWORD_WRONG_MESSAGE_LOCATOR));
    }

    public String getWrongPasswordMessageText() {
        scrollToPasswordField();
        return getWrongPasswrodMessage().getText();
    }
}


