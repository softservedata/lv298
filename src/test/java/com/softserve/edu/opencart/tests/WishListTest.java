package com.softserve.edu.opencart.tests;

import static org.testng.Assert.assertEquals;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.products.IProduct;
import com.softserve.edu.opencart.data.products.ProductRepository;
import com.softserve.edu.opencart.data.users.IUser;
import com.softserve.edu.opencart.data.users.UserRepository;
import com.softserve.edu.opencart.pages.Application;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.LoginPage;
import com.softserve.edu.opencart.pages.MyAccountPage;
import com.softserve.edu.opencart.pages.WishListPage;
import com.softserve.edu.opencart.tools.LiteralConstants;
import com.softserve.edu.opencart.tools.RegexUtils;

public class WishListTest extends TestRunner {

    private boolean containsProduct = false;

    private HomePage loginAndCheckProductInList(IUser user, IProduct product) {
        HomePage homePage = Application.get().loadHomePage().loginToHomePage(user);
        containsProduct = homePage.isWishListContainsProduct(product);
        homePage = Application.get().refreshHomePage();
        return homePage;
    }

    //1 *********Redirect to Login Page Test*********
    @Test
    public void redirectToLoginPageTest() throws Exception {
        HomePage homePage = Application.get().loadHomePage().signoutToHomePage();
        LoginPage loginPage = homePage.gotoWishListPageLogout();

        assertEquals(loginPage.getCurrentUrl(), loginPage.URL);
    }

    //
    @DataProvider(parallel = true)
    public Object[][] usersProvider() {
        return new Object[][] {
                new Object[] { UserRepository.get().customer() }
                //new Object[] { UserRepository.get().customer2() }
        };
    }

    //2 *********Login Test*********
    @Test(dataProvider = "usersProvider")
    public void loginToWishListTest(IUser user) throws Exception {
        MyAccountPage myAccountPage = Application.get().loadHomePage().signoutToHomePage().gotoLoginPage()
                .successLogin(user);
        WishListPage wishListPage = myAccountPage.gotoWishListPage();

        assertEquals(wishListPage.getCurrentUrl(), wishListPage.URL);
        Thread.sleep(1000);

        // *********Return Application To Its BeforeTest State*********
        wishListPage.signoutToHomePage();
    }
    @DataProvider
    public Object[][] userProductsProvider() {
        return new Object[][] {
                // new Object[] { UserRepository.get().customer(), ProductRepository.macBook() }
                new Object[] { UserRepository.get().customer2(), ProductRepository.iPhone() } };
    }

    @DataProvider
    public Object[][] userWithoutProductsProvider() {
        return new Object[][] { new Object[] { UserRepository.get().customer2(), ProductRepository.macBook() } };
    }

    //3 ********Wish List Contains Product Test*********
    @Test(dataProvider = "userProductsProvider")
    public void containsProductTest(IUser user, IProduct product) throws Exception {
        loginAndCheckProductInList(user, product);
        Assert.assertTrue(containsProduct);
    }

    //4 ********Wish List Does Not Contain Product*********
    @Test(dataProvider = "userWithoutProductsProvider")
    public void doesNotContainProductTest(IUser user, IProduct product) throws Exception {
        loginAndCheckProductInList(user, product);
        Assert.assertFalse(containsProduct);
    }

    //5 *********Add And Remove Product From Wish List Test*********
    @Test(dataProvider = "userProductsProvider")
    public void addRemoveTest(IUser user, IProduct product) throws Exception {
        HomePage homePage = loginAndCheckProductInList(user, product);

        homePage = homePage.addToWishListByProduct(product);
        Thread.sleep(1000);
        WishListPage wishListPage = homePage.gotoWishListPage();
        Thread.sleep(1000);
        wishListPage = wishListPage.removeFromWishListByProduct(product);

        Assert.assertFalse(wishListPage.isInWishListByProduct(product));

        // *********Return Application To Its BeforeTest State*********
        if (containsProduct) {
            homePage = wishListPage.gotoHomePage();
            Thread.sleep(1000);
            homePage = homePage.addToWishListByProduct(product);
            Thread.sleep(1000);
            homePage.signoutToHomePage();
        } else {
            wishListPage.signoutToHomePage();
        }
    }

    //6 *********Amount Test*********
    @Test(dataProvider = "userProductsProvider")
    public void amountTest(IUser user, IProduct product) throws Exception {
        int expected = 0;
        HomePage homePage = loginAndCheckProductInList(user, product);

        if (containsProduct) {
            expected = homePage.wishListAmount();
        } else {
            expected = homePage.wishListAmount() + 1;
        }
        Thread.sleep(1000);
        homePage.addToWishListByProduct(product);
        Thread.sleep(1000);

        Assert.assertEquals(homePage.wishListAmount(), expected);

        // *********Return Application To Its BeforeTest State*********
        if (!containsProduct) {
            WishListPage wishListPage = homePage.gotoWishListPage();
            wishListPage = wishListPage.removeFromWishListByProduct(product);
            homePage = wishListPage.signoutToHomePage();
        } else {
            homePage.signoutToHomePage();
        }

    }

    //7 *********Add Product To Wish List Success Notification Test*********
    @Test(dataProvider = "userProductsProvider")
    public void addToListSuccessNotificationTest(IUser user, IProduct product) throws Exception {
        HomePage homePage = loginAndCheckProductInList(user, product);
      
        Thread.sleep(1000);
        homePage = homePage.addToWishListByProduct(product);
        Thread.sleep(1000);

        Assert.assertEquals(RegexUtils.extractSuccesfullMessage(homePage.productActionNotificationText()),
                LiteralConstants.addToWishListSuccessMessage(product.getName()));
        
        Thread.sleep(1000);
        // *********Return Application To Its BeforeTest State*********
        if (!containsProduct) {
            WishListPage wishListPage = homePage.gotoWishListPage();
            wishListPage = wishListPage.removeFromWishListByProduct(product);
            homePage = wishListPage.signoutToHomePage();
        } else {
            homePage.signoutToHomePage();
        }

    }

    //8 *********Remove Product To Wish List Success Notification Test*********
    //@Test(dataProvider = "userProductsProvider")
    public void removeFromListSuccessNotificationTest(IUser user, IProduct product) throws Exception {
        HomePage homePage = loginAndCheckProductInList(user, product);
      
        Thread.sleep(1000);
        homePage = homePage.addToWishListByProduct(product);
        Thread.sleep(1000);
        
        WishListPage wishListPage = homePage.gotoWishListPage();
        
        Thread.sleep(1000);
        wishListPage = wishListPage.removeFromWishListByProduct(product);
        Thread.sleep(1000);
        Assert.assertEquals(RegexUtils.extractSuccesfullMessage(wishListPage.productActionNotificationText()),
                LiteralConstants.modifyWishListSuccessMessage());

        // *********Return Application To Its BeforeTest State*********
        if (containsProduct) {
            homePage = wishListPage.gotoHomePage();
            Thread.sleep(1000);
            homePage = homePage.addToWishListByProduct(product);
            Thread.sleep(1000);
            homePage.signoutToHomePage();
        } else {
            wishListPage.signoutToHomePage();
        }

    }

}
