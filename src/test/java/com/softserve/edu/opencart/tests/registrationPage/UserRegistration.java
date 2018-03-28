package com.softserve.edu.opencart.tests.registrationPage;

import com.softserve.edu.opencart.data.users.IUser;
import com.softserve.edu.opencart.data.users.UserRepository;
import com.softserve.edu.opencart.pages.Application;
import com.softserve.edu.opencart.pages.registration.FailRegistrationPage;
import com.softserve.edu.opencart.pages.registration.SuccessRegistrationPage;
import com.softserve.edu.opencart.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserRegistration extends TestRunner {
    @DataProvider
    public Object[][] failUserProvider() {
        return new Object[][]{{UserRepository.get().failedRegistrationUser()}};
    }

    @DataProvider
    public Object[][] successUserProvider() {
        return new Object[][]{{UserRepository.get().successRegistrationUser()}};
    }

    @Test(dataProvider = "failUserProvider")
    public void failRegistrationUser(IUser failUser) {
        String actual = Application.get()
                .loadHomePage()
                .gotoRegistrationPage()
                .verifyElements()
                .failRegistrationUser(failUser)
                .getErrorText();
        String expected = FailRegistrationPage.WRONG_EMAIL_MESSAGE;

        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "successUserProvider")
    public void successRegistrationUser(IUser successUser) {
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
    }

}