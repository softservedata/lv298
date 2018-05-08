package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.annotations.JiraTicket;
import com.softserve.edu.opencart.data.products.IProduct;
import com.softserve.edu.opencart.data.products.ProductRepository;
import com.softserve.edu.opencart.data.users.IUser;
import com.softserve.edu.opencart.data.users.UserRepository;
import com.softserve.edu.opencart.pages.*;
import com.softserve.edu.opencart.tools.LiteralConstants;
import com.softserve.edu.opencart.tools.RegexUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

@JiraTicket(type = "user story", name = "LVSET-36")
public class WishListTest extends TestRunner {

    private boolean containsProduct = false;

    @Step("Login Step for user: {0} work with product: {1}, for method: {method} step ...")
    private HomePage loginAndCheckProductInList(IUser user, IProduct product) {
        HomePage homePage = Application.get().loadHomePage().loginToHomePage
                (user);
        containsProduct = homePage.isWishListContainsProduct(product);
        homePage = Application.get().refreshHomePage();
        return homePage;
    }

    //1 *********Redirect to Login Page Test*********
    @JiraTicket(type = "test", name = "LVSET-54")
    @Test(priority = 1,description = "Redirect to Login Page Test")
    @Description("Verify that you redirected to the login page if you click on Wish List link, while you don't logged into the system.")
    @Severity(SeverityLevel.TRIVIAL)
    @Epic("Opencart Tests")
    public void redirectToLoginPageTest() {
        HomePage homePage = Application.get().loadHomePage()
                .logoutToHomePage();
        LoginPage loginPage = homePage.gotoWishListPageLogout();

        assertEquals(loginPage.getCurrentUrl(), LoginPage.URL);
    }

    //
    @DataProvider//(parallel = true)
    public Object[][] usersProvider() {
        return new Object[][]{
                new Object[]{UserRepository.get().customer()}
                //new Object[] { UserRepository.get().customer1() }
        };
    }

    //2 *********Login Test*********
    @JiraTicket(type = "test", name = "LVSET-55")
    @Test(priority = 1,description = "Log in and goto Wish List", dataProvider = "usersProvider")
    @Description("Verify that you Wish List page is opened if you click on Wish List link.")
    @Severity(SeverityLevel.TRIVIAL)
    @Epic("Opencart Tests")
    public void loginToWishListTest(IUser user) {
        MyAccountPage myAccountPage = Application.get().loadHomePage()
                .logoutToHomePage().gotoLoginPage()
                .successLogin(user);
        WishListPage wishListPage = myAccountPage.gotoWishListPage();

        assertEquals(wishListPage.getCurrentUrl(), WishListPage.URL);

        // *********Return Application To Its BeforeTest State*********
        wishListPage.logoutToHomePage();
    }

    @DataProvider
    public Object[][] userProductsProvider() {
        return new Object[][]{
                new Object[]{UserRepository.get().customer(),
                        ProductRepository.macBook()}
//                new Object[] { UserRepository.get().customer1(),
// ProductRepository.iPhone() }
        };
    }

    @DataProvider
    public Object[][] userWithoutProductsProvider() {
        return new Object[][]{new Object[]{UserRepository.get().customer1(),
                ProductRepository.macBook()}};
    }

    //3 ********Wish List Contains Product Test*********
    @JiraTicket(type = "test", name = "LVSET-56")
    @Test(priority = 3,description = "User Wish List Contains Product",dataProvider = "userProductsProvider")
    @Description("Verify that user wish list contains a specified product.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Opencart Tests")
    public void containsProductTest(IUser user, IProduct product) {
        loginAndCheckProductInList(user, product);
        Assert.assertTrue(containsProduct);
    }

    //4 ********Wish List Does Not Contain Product*********
    @JiraTicket(type = "test", name = "LVSET-57")
    @Test(priority = 3,description = "User Wish List Doesn't Contains Product",dataProvider = "userWithoutProductsProvider")
    @Description("Verify that user wish list doesn't contain a specified product.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Opencart Tests")
    public void doesNotContainProductTest(IUser user, IProduct product) {
        loginAndCheckProductInList(user, product);
        Assert.assertFalse(containsProduct);
    }

    //5 *********Add And Remove Product From Wish List Test*********
    @JiraTicket(type = "test", name = "LVSET-44")
    @Test(priority = 4,description = "Add And Remove Product From Wish List",dataProvider = "userProductsProvider")
    @Description("Verify that specified product added to Wish List correctly, then remove him from WiSh List.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Opencart Tests")
    public void addRemoveTest(IUser user, IProduct product) {
        HomePage homePage = loginAndCheckProductInList(user, product);

        homePage = homePage.addToWishListByProduct(product);
        WishListPage wishListPage = homePage.gotoWishListPage();
        wishListPage = wishListPage.removeFromWishListByProduct(product);

        Assert.assertFalse(wishListPage.isInWishListByProduct(product));

        // *********Return Application To Its BeforeTest State*********
        if (containsProduct) {
            homePage = wishListPage.gotoHomePage();
            homePage = homePage.addToWishListByProduct(product);
            homePage.logoutToHomePage();
        } else {
            wishListPage.logoutToHomePage();
        }
    }

    //6 *********Amount Test*********
    @JiraTicket(type = "test", name = "LVSET-58")
    @Test(priority = 3,description = "Wish List Content Amount",dataProvider = "userProductsProvider")
    @Description("Verify that wish list amount increased if you add a new product.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Opencart Tests")
    public void amountTest(IUser user, IProduct product) {
        int expected;
        HomePage homePage = loginAndCheckProductInList(user, product);

        if (containsProduct) {
            expected = homePage.wishListAmount();
        } else {
            expected = homePage.wishListAmount() + 1;
        }
        homePage.addToWishListByProduct(product);

        Assert.assertEquals(homePage.wishListAmount(), expected);

        // *********Return Application To Its BeforeTest State*********
        if (!containsProduct) {
            WishListPage wishListPage = homePage.gotoWishListPage();
            wishListPage = wishListPage.removeFromWishListByProduct(product);
            wishListPage.logoutToHomePage();
        } else {
            homePage.logoutToHomePage();
        }

    }

    //7 *********Add And Remove Product From Wish List Success Notification Test*********
    @JiraTicket(type = "test", name = "LVSET-59")
    @Test(priority = 1,description = "Add and Remove Product From Wish List Success Notification",dataProvider = "userProductsProvider")
    @Description("Verify that correct message shown when you add the product to wish list.")
    @Severity(SeverityLevel.TRIVIAL)
    @Epic("Opencart Tests")
    public void productActionNotificationTest(IUser user, IProduct
            product) {
        SoftAssert softAssertion= new SoftAssert();
        HomePage homePage = loginAndCheckProductInList(user, product);

        homePage = homePage.addToWishListByProduct(product);

        softAssertion.assertEquals(RegexUtils.extractSuccesfullMessage(homePage
                        .productActionNotificationText()),
                LiteralConstants.addToWishListSuccessMessage(product.getName
                        ()));
        WishListPage wishListPage = homePage.gotoWishListPage();
        wishListPage = wishListPage.removeFromWishListByProduct(product);
        softAssertion.assertEquals(RegexUtils.extractSuccesfullMessage(wishListPage
                        .productActionNotificationText()),
                LiteralConstants.MODIFY_WISH_LIST_SUCCESS_MESSAGE);

        // *********Return Application To Its BeforeTest State*********
        if (containsProduct) {
            homePage = wishListPage.gotoHomePage();
            homePage = homePage.addToWishListByProduct(product);
            homePage.logoutToHomePage();
        } else {
            wishListPage.logoutToHomePage();
        }

        softAssertion.assertAll();
    }

}
