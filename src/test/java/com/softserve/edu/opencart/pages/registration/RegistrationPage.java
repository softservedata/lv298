package com.softserve.edu.opencart.pages.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.softserve.edu.opencart.data.users.IUser;
import com.softserve.edu.opencart.pages.ARightPanel;
import com.softserve.edu.opencart.tools.JavaScriptInjection;

public class RegistrationPage extends ARightPanel {

    private final String FIELD_NAME_FIRST_NAME = "firstname";
    private final String FIELD_NAME_LAST_NAME = "lastname";
    private final String FIELD_NAME_EMAIL = "email";
    private final String FIELD_NAME_TELEPHONE = "telephone";
    private final String FIELD_NAME_FAX = "fax";
    private final String FIELD_NAME_COMPANY = "company";
    private final String FIELD_NAME_ADDRESS_1 = "address_1";
    private final String FIELD_NAME_ADDRESS_2 = "address_2";
    private final String FIELD_NAME_CITY = "city";
    private final String FIELD_NAME_POSTCODE = "postcode";
    private final String FIELD_NAME_PASSWORD = "password";
    private final String FIELD_NAME_PASSWORD_CONFIRM = "confirm";

    private final String SELECT_NAME_COUNTRY = "country_id";
    private final String SELECT_NAME_REGION = "zone_id";
    private final String RADIO_NAME_SUBSCRIBE = "newsletter";
    private final String CHECKBOX_NAME_AGREE = "agree";

    private final String BUTTON_CLASS_CONTINUE = "input.btn-primary";

    protected WebDriver driver;

    public RegistrationPage(WebDriver driver) {
	super(driver);
	this.driver = driver;
    }

    // First name field;
    public WebElement getFirstNameField() {
	return driver.findElement(By.name(FIELD_NAME_FIRST_NAME));
    }

    public void sendTextToFirstNameField(String text) {
	getFirstNameField().clear();
	getFirstNameField().click();
	getFirstNameField().sendKeys(text);
    }

    // Last name field;
    public WebElement getLastNameField() {
	return driver.findElement(By.name(FIELD_NAME_LAST_NAME));
    }

    public void sendTextToLastNameField(String text) {
	getLastNameField().clear();
	getLastNameField().click();
	getLastNameField().sendKeys(text);
    }

    // Email field
    public WebElement getEmailField() {
	return driver.findElement(By.name(FIELD_NAME_EMAIL));
    }

    public void sendTextToEmailField(String text) {
	getEmailField().clear();
	getEmailField().click();
	getEmailField().sendKeys(text);
    }

    // Telephone field
    public WebElement getTelephoneField() {
	return driver.findElement(By.name(FIELD_NAME_TELEPHONE));
    }

    public void sendTextToTelephoneField(String text) {
	getTelephoneField().clear();
	getTelephoneField().click();
	getTelephoneField().sendKeys(text);
    }

    // Fax Field
    public WebElement getFaxField() {
	return driver.findElement(By.name(FIELD_NAME_FAX));
    }

    public void sendTextToFaxField(String text) {
	getFaxField().clear();
	getFaxField().click();
	getFaxField().sendKeys(text);
    }

    // Company Field
    public WebElement getCompanyField() {
	return driver.findElement(By.name(FIELD_NAME_COMPANY));
    }

    public void sendTextToCompanyField(String text) {
	getCompanyField().clear();
	getCompanyField().click();
	getCompanyField().sendKeys(text);
    }

    // Address 1 field
    public WebElement getAddress1Field() {
	return driver.findElement(By.name(FIELD_NAME_ADDRESS_1));
    }

    public void sendTextToAddress1Field(String text) {
	getAddress1Field().clear();
	getAddress1Field().click();
	getAddress1Field().sendKeys(text);
    }

    // Address 2 field
    public WebElement getAddress2Field() {
	return driver.findElement(By.name(FIELD_NAME_ADDRESS_2));
    }

    public void sendTextToAddress2Field(String text) {
	getAddress2Field().clear();
	getAddress2Field().click();
	getAddress2Field().sendKeys(text);
    }

    // City Field
    public WebElement getCityField() {
	return driver.findElement(By.name(FIELD_NAME_CITY));
    }

    public void sendTextToCityField(String text) {
	getCityField().clear();
	getCityField().click();
	getCityField().sendKeys(text);
    }

    // Postcode field
    public WebElement getPostcodeField() {
	return driver.findElement(By.name(FIELD_NAME_POSTCODE));
    }

    public void sendTextToPostcodeField(String text) {
	getPostcodeField().clear();
	getPostcodeField().click();
	getPostcodeField().sendKeys(text);
    }

    // Password field
    public WebElement getPasswordField() {
	return driver.findElement(By.name(FIELD_NAME_PASSWORD));
    }

    public void sendTextToPasswordField(String text) {
	getPasswordField().clear();
	getPasswordField().click();
	getPasswordField().sendKeys(text);
    }

    // Password Confirm field
    public WebElement getPasswordConfirmField() {
	return driver.findElement(By.name(FIELD_NAME_PASSWORD_CONFIRM));
    }

    public void sendTextToPasswordConfirmField(String text) {
	getPasswordConfirmField().clear();
	getPasswordConfirmField().click();
	getPasswordConfirmField().sendKeys(text);
    }

    // Country Selector
    public WebElement getCountrySelector() {
	return driver.findElement(By.name(SELECT_NAME_COUNTRY));
    }

    public Select toSelectCountry() {
	return new Select(getCountrySelector());
    }

    public void clickCountrySelector() {
	getCountrySelector().click();
    }

    public void selectCountryByValue(String country) {
	toSelectCountry().selectByValue(country);
    }

    // State Selector
    public WebElement getStateSelector() {
	return driver.findElement(By.name(SELECT_NAME_REGION));
    }

    public Select toSelectStateSelector() {
	return new Select(getStateSelector());
    }

    public void clickStateSelector() {
	getStateSelector().click();
    }

    public void selectStateByValue(String state) {
	toSelectStateSelector().selectByValue(state);
    }

    // Subscribe Radio Button
    public WebElement getSubscribeRadio() {
	return driver.findElement(By.name(RADIO_NAME_SUBSCRIBE));
    }

    public void selectSubscribe(boolean isSubscribe) {
	if (isSubscribe) {
	    getSubscribeRadio().click();
	}
    }

    // Agree Check Box
    public WebElement getAgreeCheckBox() {
	return driver.findElement(By.name(CHECKBOX_NAME_AGREE));
    }

    public void clickAgreeCheckBox() {
	getAgreeCheckBox().click();
    }

    // Continue Button
    public WebElement getContinueButton() {
	return driver.findElement(By.cssSelector(BUTTON_CLASS_CONTINUE));
    }

    public void clickContinueButton() {
	getContinueButton().click();
    }

    // Test and BL
    public void verifyElements() {
	getFirstNameField();
	getLastNameField();
	getEmailField();
	getTelephoneField();
	getFaxField();
	getCompanyField();
	getAddress1Field();
	getAddress2Field();
	getCityField();
	getPostcodeField();
	getPasswordField();
	getPasswordConfirmField();
	getCountrySelector();
	getStateSelector();
	getSubscribeRadio();
	getAgreeCheckBox();
	getContinueButton();
    }

    public RegistrationPage registrationUser(IUser user) {
	sendTextToFirstNameField(user.getFirstname());
	JavaScriptInjection.inject().scroll(driver, getFirstNameField());
	sendTextToLastNameField(user.getLastname());
	sendTextToEmailField(user.getEmail());
	JavaScriptInjection.inject().scroll(driver, getTelephoneField());
	sendTextToTelephoneField(user.getTelephone());
	if (user.getFax() != null) {
	    sendTextToFaxField(user.getFax());
	}
	if (user.getCompany() != null) {
	    sendTextToCompanyField(user.getCompany());
	}
	sendTextToAddress1Field(user.getAddressMain());
	if (user.getAddressAdditional() != null) {
	    sendTextToAddress2Field(user.getAddressAdditional());
	}
	sendTextToCityField(user.getCity());
	if (user.getPostCode() != null) {
	    sendTextToPostcodeField(user.getPostCode());
	}
	selectCountryByValue(user.getCountry());
	selectStateByValue(user.getState());
	JavaScriptInjection.inject().scroll(driver, getPasswordField());
	sendTextToPasswordField(user.getPassword());
	sendTextToPasswordConfirmField(user.getPassword());
	selectSubscribe(user.isSubscribe());
	clickAgreeCheckBox();
	clickContinueButton();
	return new RegistrationPage(driver);
    }
    
    public FailRegistrationPage failRegistrationUser(IUser user) {
	registrationUser(user);
	return new FailRegistrationPage(driver);
    }
    
    public SuccessRegistrationPage successRegistrationUser(IUser user) {
	registrationUser(user);
	return new SuccessRegistrationPage(driver);
    }
}
