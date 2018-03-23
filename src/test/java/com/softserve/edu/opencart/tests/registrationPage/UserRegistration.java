package com.softserve.edu.opencart.tests.registrationPage;

import com.softserve.edu.opencart.data.users.IUser;
import com.softserve.edu.opencart.data.users.UserRepository;
import com.softserve.edu.opencart.pages.Application;
import com.softserve.edu.opencart.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserRegistration extends TestRunner {
    @DataProvider
    public Object[][] failUserProvider() {
        return new Object[][]{{UserRepository.get().customer()}};
    }

    @DataProvider
    public Object[][] successUserProvider() {
        return new Object[][]{{UserRepository.get().nazar()}};
    }

    @Test(dataProvider = "failUserProvider")
    public void failRegistrationUser(IUser failUser) throws InterruptedException {
        String actual = Application.get()
                .loadHomePage()
                .gotoRegistrationPage()
                .failRegistrationUser(failUser)
                .getErrorText();
        String expected = "Warning: E-Mail Address is already registered!";
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "successUserProvider")
    public void successRegistrationUser(IUser successUser) throws InterruptedException {
        String actual = Application.get()
                .loadHomePage()
                .gotoRegistrationPage()
                .successRegistrationUser(successUser)
                .getSuccessfullRegistrationText();
        String expeected = "Your Account Has Been Created!";
        Assert.assertEquals(actual, expeected);
        //Logout from created user profiel
        Application.get().loadHomePage().signoutToHomePage();
    }
}