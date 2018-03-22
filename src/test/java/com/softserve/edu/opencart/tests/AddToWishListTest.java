package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.products.IProduct;
import com.softserve.edu.opencart.data.products.ProductRepository;
import com.softserve.edu.opencart.data.users.IUser;
import com.softserve.edu.opencart.data.users.UserRepository;
import com.softserve.edu.opencart.pages.Application;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.MyAccountPage;
import com.softserve.edu.opencart.pages.WishListPage;
import com.softserve.edu.opencart.tools.LiteralConstants;
import com.softserve.edu.opencart.tools.RegexUtils;

public class AddToWishListTest extends TestRunner {
    @DataProvider
    public Object[][] usersProvider() {
        return new Object[][] { { UserRepository.get().customer() } };
    }
    
    // *********Login Test*********
    @Test(dataProvider = "usersProvider")
    public void loginToHomePageTest(IUser user) throws Exception {
        MyAccountPage myAccountPage = Application.get().loadHomePage().gotoLoginPage().successLogin(user);
        // Thread.sleep(2000);
        Assert.assertEquals(myAccountPage.getMyAccountLabelText(), MyAccountPage.MY_ACCOUNT_LABEL_TEXT);
        // Thread.sleep(2000);
        HomePage homePage = myAccountPage.gotoHomePage();

        Assert.assertTrue(homePage.isLogged());
        // Thread.sleep(2000);
    }

    @DataProvider
    public Object[][] productsProvider() {
        return new Object[][] { { ProductRepository.macBook() } };
    }

    // *********Amount and Successful Message Test*********
    @Test(dataProvider = "productsProvider", dependsOnMethods = { "loginToHomePageTest" })
    public void amountTest(IProduct product) throws Exception {
        HomePage homePage = Application.get().loadHomePage();
        int expected = homePage.wishListAmount() + 1;
        Thread.sleep(1000);
        homePage.addToWishListByProduct(product.getName());
        Thread.sleep(1000);
        //successfulTextTest
        Assert.assertEquals(RegexUtils.extractSuccesfullMessage(homePage.productActionNotificationText()),
                LiteralConstants.addToWishListSuccessMessage(product.getName()));
        int actual = homePage.wishListAmount();
        Thread.sleep(1000);
        Assert.assertEquals(actual, expected);
    }
/*
    @Test(dataProvider = "productsProvider", dependsOnMethods = { "loginToHomePageTest" })
    public void successfulTextTest(IProduct product) throws Exception {
        HomePage homePage = Application.get().loadHomePage();
        Thread.sleep(1000);
        //homePage.addToWishListByProduct(product.getName());
        Thread.sleep(1000);     
         Assert.assertEquals(RegexUtils.extractSuccesfullMessage(homePage.productActionNotificationText()),
                LiteralConstants.addToWishListSuccessMessage(product.getName())); 
        
    }
  */  
    // *********Is in Wish List Test*********
    @Test(dataProvider = "productsProvider", dependsOnMethods = { "loginToHomePageTest" })
    public void isInListTest(IProduct product) throws Exception {
        HomePage homePage = Application.get().loadHomePage();
        //homePage.addToWishListByProduct(product.getName());
        Thread.sleep(1000);
        WishListPage wishListPage = homePage.gotoWishListPage();
        Thread.sleep(1000);
        Assert.assertTrue(wishListPage.getProductComponentNames().contains(product.getName()));
    }
    
    // *********Remove Product From Wish List Test*********
    @Test(dataProvider = "productsProvider", dependsOnMethods = { "isInListTest" })
    public void removeFromListTest(IProduct product) throws Exception {
        WishListPage wishListPage = Application.get().loadWishListPage();
        Thread.sleep(1000);
        wishListPage = wishListPage.removeFromWishListByProduct(product.getName());
        Assert.assertFalse(wishListPage.isInWishListByProductName(product.getName()));
    }

}
