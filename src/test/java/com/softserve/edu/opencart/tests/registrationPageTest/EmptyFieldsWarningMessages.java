package com.softserve.edu.opencart.tests.registrationPageTest;

import com.softserve.edu.opencart.pages.Application;
import com.softserve.edu.opencart.pages.registration.EmptyFieldsRegistrationPage;
import com.softserve.edu.opencart.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EmptyFieldsWarningMessages extends TestRunner {
    private EmptyFieldsRegistrationPage emptyFieldsRegistrationPage;

    @BeforeClass
    public void beforeClass() {
        logger.debug("Before Class EmptyFieldsRegistrationPage");
        emptyFieldsRegistrationPage = Application.get().loadHomePage().gotoRegistrationPage().emptyFieldsRegistrationPage();
    }

    @Test(testName = "Check if First Name Wrong Message is appear",
            description = "Check if first name wrong message is appear",
            priority = 1)
    public void checkErrorMessageNearFirstNameField() {
        logger.debug("checkTheErrorMessageNearFirstNameField STARTED");
        String actual = emptyFieldsRegistrationPage.getWrongFirsNameMessageText();
        String expected = emptyFieldsRegistrationPage.WRONG_FIRST_NAME_MESSAGE;

        Assert.assertEquals(actual, expected);
        logger.debug("checkTheErrorMessageNearFirstNameField Finished");
    }

    @Test(testName = "Check if Last Name Wrong Message is appear")
    public void checkErrorMessageNearLastNameField() {
        logger.debug("checkTheErrorMessageNearLastNameField STARTED");
        String actual = emptyFieldsRegistrationPage.getWrongLastNameMessageText();
        String expected = emptyFieldsRegistrationPage.WRONG_LAST_NAME_MESSAGE;

        Assert.assertEquals(actual, expected);
        logger.debug("checkTheErrorMessageNearLastNameField FINISHED");
    }

    @Test(testName = "Check if E-mail Wrong Message is appear")
    public void checkErrorMessageNearEmailField() {
        logger.debug("checkErrorMessageNearEmailField STARTED");
        String actual = emptyFieldsRegistrationPage.getWrongEmailMessageText();
        String expected = emptyFieldsRegistrationPage.WRONG_EMAIL_MESSAGE;

        Assert.assertEquals(actual, expected);
        logger.debug("checkErrorMessageNearEmailField FINISHED");
    }

    @Test(testName = "Check if Telephone Wrong Message is appear")
    public void checkErrorMessageNearTelephoneField() {
        logger.debug("checkErrorMessageNearTelephoneField STARTED");
        String actual = emptyFieldsRegistrationPage.getWrongTelephoneMessageText();
        String expected = emptyFieldsRegistrationPage.WRONG_TELEPHONE_MESSAGE;

        Assert.assertEquals(actual, expected);
        logger.debug("checkErrorMessageNearTelephoneField FINISHED");
    }

    @Test(testName = "Check if Main Address Wrong Message is appear")
    public void checkErrorMessageNearMainAddressField() {
        logger.debug("checkErrorMessageNearMainAddressField STARTED");
        String actual = emptyFieldsRegistrationPage.getWrongMainAddressMessageText();
        String expected = emptyFieldsRegistrationPage.WRONG_MAIN_ADDRESS_MESSAGE;

        Assert.assertEquals(actual, expected);
        logger.debug("checkErrorMessageNearMainAddressField FINISHED");
    }

    @Test(testName =  "Check if City Wrong Message is appear")
    public void checkErrorMessageNearCityField(){
        logger.debug("checkErrorMessageNearCityField STARTED");
        String actual = emptyFieldsRegistrationPage.getWrongCityMessageText();
        String expected = emptyFieldsRegistrationPage.WRONG_CITY_MESSAGE;

        Assert.assertEquals(actual, expected);
        logger.debug("checkErrorMessageNearCityField FINISHED");
    }

    @Test(testName = "Check if Postcode Wrong Message is appear")
    public void checkErrorMessageNearPostcodeField(){
        logger.debug("checkErrorMessageNearPostcodeField STARTED");
        String actual = emptyFieldsRegistrationPage.getWrongPostcodeMessageText();
        String expected = emptyFieldsRegistrationPage.WRONG_POSTCODE_MESSAGE;

        Assert.assertEquals(actual, expected);
        logger.debug("checkErrorMessageNearPostcodeField FINISHED");
    }

    @Test(testName = "Check if State Wrong Message is appear")
    public void checkErrorMessageNearStateSelector(){
        logger.debug("checkErrorMessageNearStateSelector STARTED");
        String actual = emptyFieldsRegistrationPage.getWrongStateMessageText();
        String expected = emptyFieldsRegistrationPage.WRONG_STATE_MESSAGE;

        Assert.assertEquals(actual, expected);
        logger.debug("checkErrorMessageNearStateSelector FINISHED");
    }

    @Test(testName = "Check if Password WrongMessage is appear")
    public void checkErrorMessageNearPasswordField(){
        logger.debug("checkErrorMessageNearPasswordField STARTED");
        String actual = emptyFieldsRegistrationPage.getWrongPasswordMessageText();
        String expected = emptyFieldsRegistrationPage.WRONG_PASSWORD_MESSAGE;

        Assert.assertEquals(actual, expected);
        logger.debug("checkErrorMessageNearPasswordField FINISHED");
    }

}
