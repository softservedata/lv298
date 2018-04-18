package com.softserve.edu.opencart.tests.registrationPageTest;

import com.softserve.edu.opencart.data.users.IUser;
import com.softserve.edu.opencart.data.users.UserRepository;
import com.softserve.edu.opencart.pages.Application;
import com.softserve.edu.opencart.pages.registration.FailRegistrationPage;
import com.softserve.edu.opencart.pages.registration.SuccessRegistrationPage;
import com.softserve.edu.opencart.tests.TestRunner;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic ("Functional test for User Registration")
public class UserRegistration extends TestRunner {


    @DataProvider
    public Object[][] failUserProvider() {
        return new Object[][]{{UserRepository.get().failedRegistrationUser()}};
    }

    @DataProvider
    public Object[][] successUserProvider() {
        return new Object[][]{{UserRepository.get().successRegistrationUser()}};
    }

    @Feature("Registration Page")
    @Test(dataProvider = "failUserProvider"
            ,priority = 5
            ,description = "Check if system will not registrate user with exist email")
    public void failRegistrationUser(IUser failUser) {
        logger.debug("Test failRegistrationUser STARTED");
        Reporter.log("<b>Test no1</b> " + Reporter.getCurrentTestResult());

        String actual = Application.get()
                .loadHomePage()
                .gotoRegistrationPage()
                .verifyElements()
                .failRegistrationUser(failUser)
                .getErrorText();
        String expected = FailRegistrationPage.WRONG_EMAIL_MESSAGE;

        Assert.assertEquals(actual, expected);

        logger.debug("Test failRegistrationUser FINISHED");
    }

    @Feature("Registration Page")
    @Test(dataProvider = "successUserProvider"
            ,priority = 5
            ,description = "Check if system will registrate new user")
    public void successRegistrationUser(IUser successUser) {
        logger.debug("Test succesRegistrationUser STARTED");

        String actual = Application.get()
                .loadHomePage()
                .gotoRegistrationPage()
                .verifyElements()
                .successRegistrationUser(successUser)
                .getSuccessfullRegistrationText();
        String expeected = SuccessRegistrationPage.SUCCSESS_REGISTRATION_MESSAGE;

        Assert.assertEquals(actual, expeected);

        //Logout from created user profile

        Application.get().loadHomePage().signoutToHomePage();
        logger.debug("Test successRegistrationUser FINISHED");
    }


}