package com.softserve.edu.opencart.pages.registration;

import com.softserve.edu.opencart.data.users.IUser;
import com.softserve.edu.opencart.pages.ARightPanel;
import com.softserve.edu.opencart.tools.JavaScriptInjection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * This class describe registration page object.
 */
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

    public void scrollToFirstNameField() {
        JavaScriptInjection.inject().scroll(driver, getFirstNameField());
    }

    public void sendTextToFirstNameField(String text) {
        scrollToFirstNameField();
        getFirstNameField().clear();
        getFirstNameField().click();
        getFirstNameField().sendKeys(text);
    }

    // Last name field;
    public WebElement getLastNameField() {
        return driver.findElement(By.name(FIELD_NAME_LAST_NAME));
    }

    public void scrollToLastNameField() {
        JavaScriptInjection.inject().scroll(driver, getLastNameField());
    }

    public void sendTextToLastNameField(String text) {
        scrollToLastNameField();
        getLastNameField().clear();
        getLastNameField().click();
        getLastNameField().sendKeys(text);
    }

    // Email field
    public WebElement getEmailField() {
        return driver.findElement(By.name(FIELD_NAME_EMAIL));
    }

    public void scrollToEmailField() {
        JavaScriptInjection.inject().scroll(driver, getEmailField());
    }

    public void sendTextToEmailField(String text) {
        scrollToEmailField();
        getEmailField().clear();
        getEmailField().click();
        getEmailField().sendKeys(text);
    }

    // Telephone field
    public WebElement getTelephoneField() {
        return driver.findElement(By.name(FIELD_NAME_TELEPHONE));
    }

    public void scrollToTelephoneField() {
        JavaScriptInjection.inject().scroll(driver, getTelephoneField());
    }

    public void sendTextToTelephoneField(String text) {
        scrollToTelephoneField();
        getTelephoneField().clear();
        getTelephoneField().click();
        getTelephoneField().sendKeys(text);
    }

    // Fax Field
    public WebElement getFaxField() {
        return driver.findElement(By.name(FIELD_NAME_FAX));
    }

    public void scrollToFaxField() {
        JavaScriptInjection.inject().scroll(driver, getFaxField());
    }

    public void sendTextToFaxField(String text) {
        scrollToFaxField();
        getFaxField().clear();
        getFaxField().click();
        getFaxField().sendKeys(text);
    }

    // Company Field
    public WebElement getCompanyField() {
        return driver.findElement(By.name(FIELD_NAME_COMPANY));
    }

    public void scrollToCompanyField() {
        JavaScriptInjection.inject().scroll(driver, getCompanyField());
    }

    public void sendTextToCompanyField(String text) {
        scrollToCompanyField();
        getCompanyField().clear();
        getCompanyField().click();
        getCompanyField().sendKeys(text);
    }

    // Main address field
    public WebElement getMainAddressField() {
        return driver.findElement(By.name(FIELD_NAME_ADDRESS_1));
    }

    public void scrollToMainAddressField() {
        JavaScriptInjection.inject().scroll(driver, getMainAddressField());
    }

    public void sendTextToMainAddressField(String text) {
        scrollToMainAddressField();
        getMainAddressField().clear();
        getMainAddressField().click();
        getMainAddressField().sendKeys(text);
    }

    // Additional address field
    public WebElement getAdditionalAddressField() {
        return driver.findElement(By.name(FIELD_NAME_ADDRESS_2));
    }

    public void scrollToAdditionalAddressField() {
        JavaScriptInjection.inject().scroll(driver, getMainAddressField());
    }

    public void sendTextToAdditionalAddressField(String text) {
        scrollToAdditionalAddressField();
        getAdditionalAddressField().clear();
        getAdditionalAddressField().click();
        getAdditionalAddressField().sendKeys(text);
    }

    // City Field
    public WebElement getCityField() {
        return driver.findElement(By.name(FIELD_NAME_CITY));
    }

    public void scrollToCityField() {
        JavaScriptInjection.inject().scroll(driver, getCityField());
    }

    public void sendTextToCityField(String text) {
        scrollToCityField();
        getCityField().clear();
        getCityField().click();
        getCityField().sendKeys(text);
    }

    // Postcode field
    public WebElement getPostcodeField() {
        return driver.findElement(By.name(FIELD_NAME_POSTCODE));
    }

    public void scrollToPostcodeField() {
        JavaScriptInjection.inject().scroll(driver, getPostcodeField());
    }

    public void sendTextToPostcodeField(String text) {
        scrollToPostcodeField();
        getPostcodeField().clear();
        getPostcodeField().click();
        getPostcodeField().sendKeys(text);
    }

    // Password field
    public WebElement getPasswordField() {
        return driver.findElement(By.name(FIELD_NAME_PASSWORD));
    }

    public void scrollToPasswordField() {
        JavaScriptInjection.inject().scroll(driver, getPasswordField());
    }

    public void sendTextToPasswordField(String text) {
        scrollToPasswordField();
        getPasswordField().clear();
        getPasswordField().click();
        getPasswordField().sendKeys(text);
    }

    // Password Confirm field
    public WebElement getPasswordConfirmField() {
        return driver.findElement(By.name(FIELD_NAME_PASSWORD_CONFIRM));
    }

    public void scrollToPasswordConfirmField() {
        JavaScriptInjection.inject().scroll(driver, getPasswordConfirmField());
    }

    public void sendTextToPasswordConfirmField(String text) {
        scrollToPasswordConfirmField();
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

    // Tests methods and Bussines logics.

    /**
     * Method for test, which allow us to verify if all items are present on the page.
     */
    public RegistrationPage verifyElements() {
        getFirstNameField();
        getLastNameField();
        getEmailField();
        getTelephoneField();
        getFaxField();
        getCompanyField();
        getMainAddressField();
        getAdditionalAddressField();
        getCityField();
        getPostcodeField();
        getPasswordField();
        getPasswordConfirmField();
        getCountrySelector();
        getStateSelector();
        getSubscribeRadio();
        getAgreeCheckBox();
        getContinueButton();
        return this;
    }

    /**
     *
     * @param user data with information about user. All necessary fields on registration page may be filled.
     * @return after click continue button create a new object of Registration page;
     */
    public RegistrationPage registrationUser(IUser user) {
        sendTextToFirstNameField(user.getFirstname());
        sendTextToLastNameField(user.getLastname());
        sendTextToEmailField(user.getEmail());
        sendTextToTelephoneField(user.getTelephone());
        if (user.getFax() != null) {
            sendTextToFaxField(user.getFax());
        }
        if (user.getCompany() != null) {
            sendTextToCompanyField(user.getCompany());
        }
        sendTextToMainAddressField(user.getAddressMain());
        if (user.getAddressAdditional() != null) {
            sendTextToAdditionalAddressField(user.getAddressAdditional());
        }
        sendTextToCityField(user.getCity());
        if (user.getPostCode() != null) {
            sendTextToPostcodeField(user.getPostCode());
        }
        selectCountryByValue(user.getCountry());
        selectStateByValue(user.getState());

        sendTextToPasswordField(user.getPassword());
        sendTextToPasswordConfirmField(user.getPassword());
        selectSubscribe(user.isSubscribe());
        clickAgreeCheckBox();
        clickContinueButton();
        return new RegistrationPage(driver);
    }


    /**
     *
     * @param user data with information about user. All necessary fields on registration page must be filled.
     * @return after click continue button create a new object of Fail Registration page;
     */
    public FailRegistrationPage failRegistrationUser(IUser user) {
        registrationUser(user);
        return new FailRegistrationPage(driver);
    }

    /**
     *
     * @param user data with information about user. All necessary fields on registration page must be filled.
     * @return after click continue button create a new object of Success Registration page;
     */
    public SuccessRegistrationPage successRegistrationUser(IUser user) {
        registrationUser(user);
        return new SuccessRegistrationPage(driver);
    }
}
