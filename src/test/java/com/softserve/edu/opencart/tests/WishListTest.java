package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.annotations.JiraTicket;
import com.softserve.edu.opencart.data.products.IProduct;
import com.softserve.edu.opencart.data.products.ProductRepository;
import com.softserve.edu.opencart.data.users.IUser;
import com.softserve.edu.opencart.data.users.UserRepository;
import com.softserve.edu.opencart.pages.*;
import com.softserve.edu.opencart.tools.LiteralConstants;
import com.softserve.edu.opencart.tools.RegexUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@JiraTicket(type = "user story", name = "LVSET-36")
public class WishListTest extends TestRunner {

    private boolean containsProduct = false;

    private HomePage loginAndCheckProductInList(IUser user, IProduct product) {
        HomePage homePage = Application.get().loadHomePage().loginToHomePage
                (user);
        containsProduct = homePage.isWishListContainsProduct(product);
        homePage = Application.get().refreshHomePage();
        return homePage;
    }

    //1 *********Redirect to Login Page Test*********
    @JiraTicket(type = "test", name = "LVSET-54")
    @Test
    public void redirectToLoginPageTest() {
        HomePage homePage = Application.get().loadHomePage()
                .signoutToHomePage();
        LoginPage loginPage = homePage.gotoWishListPageLogout();

        assertEquals(loginPage.getCurrentUrl(), loginPage.URL);
    }

    //
    @DataProvider//(parallel = true)
    public Object[][] usersProvider() {
        return new Object[][]{
                new Object[]{UserRepository.get().customer()}
                //new Object[] { UserRepository.get().customer2() }
        };
    }

    //2 *********Login Test*********w
    @JiraTicket(type = "test", name = "LVSET-55")
    @Test(dataProvider = "usersProvider")
    public void loginToWishListTest(IUser user) {
        MyAccountPage myAccountPage = Application.get().loadHomePage()
                .signoutToHomePage().gotoLoginPage()
                .successLogin(user);
        WishListPage wishListPage = myAccountPage.gotoWishListPage();

        assertEquals(wishListPage.getCurrentUrl(), wishListPage.URL);

        // *********Return Application To Its BeforeTest State*********
        wishListPage.signoutToHomePage();
    }

    @DataProvider
    public Object[][] userProductsProvider() {
        return new Object[][]{
                new Object[]{UserRepository.get().customer(),
                        ProductRepository.macBook()}
//                new Object[] { UserRepository.get().customer2(),
// ProductRepository.iPhone() }
        };
    }

    @DataProvider
    public Object[][] userWithoutProductsProvider() {
        return new Object[][]{new Object[]{UserRepository.get().customer2(),
                ProductRepository.macBook()}};
    }

    //3 ********Wish List Contains Product Test*********
    @JiraTicket(type = "test", name = "LVSET-56")
    @Test(dataProvider = "userProductsProvider")
    public void containsProductTest(IUser user, IProduct product) {
        loginAndCheckProductInList(user, product);
        Assert.assertTrue(containsProduct);
    }

    //4 ********Wish List Does Not Contain Product*********
    @JiraTicket(type = "test", name = "LVSET-57")
    @Test(dataProvider = "userWithoutProductsProvider")
    public void doesNotContainProductTest(IUser user, IProduct product) {
        loginAndCheckProductInList(user, product);
        Assert.assertFalse(containsProduct);
    }

    //5 *********Add And Remove Product From Wish List Test*********
    @JiraTicket(type = "test", name = "LVSET-44")
    @Test(dataProvider = "userProductsProvider")
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
            homePage.signoutToHomePage();
        } else {
            wishListPage.signoutToHomePage();
        }
    }

    //6 *********Amount Test*********
    @Test(dataProvider = "userProductsProvider")
    @JiraTicket(type = "test", name = "LVSET-58")
    public void amountTest(IUser user, IProduct product) throws Exception {
        int expected = 0;
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
            wishListPage.signoutToHomePage();
        } else {
            homePage.signoutToHomePage();
        }

    }

    //7 *********Add Product To Wish List Success Notification Test*********
    @JiraTicket(type = "test", name = "LVSET-59")
    @Test(dataProvider = "userProductsProvider")
    public void addToListSuccessNotificationTest(IUser user, IProduct
            product) {
        HomePage homePage = loginAndCheckProductInList(user, product);

        homePage = homePage.addToWishListByProduct(product);

        Assert.assertEquals(RegexUtils.extractSuccesfullMessage(homePage
                        .productActionNotificationText()),
                LiteralConstants.addToWishListSuccessMessage(product.getName
                        ()));

        // *********Return Application To Its BeforeTest State*********
        if (!containsProduct) {
            WishListPage wishListPage = homePage.gotoWishListPage();
            wishListPage = wishListPage.removeFromWishListByProduct(product);
            wishListPage.signoutToHomePage();
        } else {
            homePage.signoutToHomePage();
        }

    }

    //TODO colaborate with Add Product To Wish List Success Notification Test
    //8 *********Remove Product To Wish List Success Notification Test*********
    //@Test(dataProvider = "userProductsProvider")
    public void removeFromListSuccessNotificationTest(IUser user, IProduct
            product) throws Exception {
        HomePage homePage = loginAndCheckProductInList(user, product);

//        Thread.sleep(1000);
        homePage = homePage.addToWishListByProduct(product);
//        Thread.sleep(1000);

        WishListPage wishListPage = homePage.gotoWishListPage();

//        Thread.sleep(1000);
        wishListPage = wishListPage.removeFromWishListByProduct(product);
//        Thread.sleep(1000);
        Assert.assertEquals(RegexUtils.extractSuccesfullMessage(wishListPage
                        .productActionNotificationText()),
                LiteralConstants.MODIFY_WISH_LIST_SUCCESS_MESSAGE);

        // *********Return Application To Its BeforeTest State*********
        if (containsProduct) {
            homePage = wishListPage.gotoHomePage();
//            Thread.sleep(1000);
            homePage = homePage.addToWishListByProduct(product);
//            Thread.sleep(1000);
            homePage.signoutToHomePage();
        } else {
            wishListPage.signoutToHomePage();
        }

    }

}
