package com.softserve.edu.opencart.tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.App;
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

public class WishListTest extends TestRunner {
    
    private boolean containsProduct = false;
    
    private HomePage defaultAction(IUser user,IProduct product) {
        HomePage homePage = Application.get().loadHomePage().loginToHomePage(user);
        containsProduct = homePage.isWishListContainsProduct(product);
        homePage = Application.get().refreshHomePage();
        return homePage;
    }

    @DataProvider
    public Object[][] productsProvider() {
        return new Object[][] { new Object[] { ProductRepository.macBook() } };
    }

    @DataProvider
    public Object[][] usersProvider() {
        return new Object[][] { new Object[] { UserRepository.get().customer() } };
    }

    // *********Login Test*********
    // @Test(dataProvider = "usersProvider")
    public void loginToHomePageTest(IUser user) throws Exception {
        MyAccountPage myAccountPage = Application.get().loadHomePage().gotoLoginPage().successLogin(user);
        Assert.assertEquals(myAccountPage.getMyAccountLabelText(), MyAccountPage.MY_ACCOUNT_LABEL_TEXT);
        HomePage homePage = myAccountPage.signoutToHomePage();
        Assert.assertFalse(homePage.isLogged());
    }
    
    // *********Data Provider For Below Tests********
    @DataProvider
    public Object[][] userProductsProvider() {
        return new Object[][] { 
            //new Object[] { UserRepository.get().customer(), ProductRepository.macBook() }
            new Object[] { UserRepository.get().customer2(),ProductRepository.iPhone() }
        };
    }

    // *********Add And Remove Product From Wish List Test*********
    // @Test(dataProvider = "userProductsProvider")
    public void addRemoveTest(IUser user, IProduct product) throws Exception {
        HomePage homePage = Application.get().loadHomePage().loginToHomePage(user);
        homePage = homePage.addToWishListByProduct(product);

        Thread.sleep(1000);
        WishListPage wishListPage = homePage.gotoWishListPage();
        Thread.sleep(1000);
        wishListPage = wishListPage.removeFromWishListByProduct(product);

        Assert.assertFalse(wishListPage.isInWishListByProduct(product));

        // *********Cleaning*********
        homePage = wishListPage.signoutToHomePage();
    }

    // *********Amount Test*********
   // @Test(dataProvider = "userProductsProvider")
    public void amountTest(IUser user, IProduct product) throws Exception {
        int expected = 0;
       // boolean containsProduct = false;

        HomePage homePage = defaultAction(user, product);
//        HomePage homePage = Application.get().loadHomePage().loginToHomePage(user);
//        containsProduct = homePage.isWishListContainsProduct(product);
//        homePage = Application.get().refreshHomePage();
        
        if (containsProduct) {
            expected = homePage.wishListAmount();
        } else {
            expected = homePage.wishListAmount() + 1;
        }
        Thread.sleep(1000);
        
        homePage.addToWishListByProduct(product);
        Thread.sleep(1000);

        Assert.assertEquals(homePage.wishListAmount(), expected);

        // *********Cleaning*********
        if (!containsProduct) {
            WishListPage wishListPage = homePage.gotoWishListPage();
            wishListPage = wishListPage.removeFromWishListByProduct(product);
            homePage = wishListPage.signoutToHomePage();
        } else {
            homePage.signoutToHomePage();
        }

    }

    // *********Add Product To Wish List Success Notification Test*********
    //@Test(dataProvider = "userProductsProvider")
    public void addToListSuccessNotificationTest(IUser user, IProduct product) throws Exception {
        //boolean containsProduct = false;

//        HomePage homePage = Application.get().loadHomePage().loginToHomePage(user);
//        containsProduct = homePage.isWishListContainsProduct(product);
        HomePage homePage = defaultAction(user, product);
        Thread.sleep(1000);

        homePage = homePage.addToWishListByProduct(product);
        Thread.sleep(1000);

        Assert.assertEquals(RegexUtils.extractSuccesfullMessage(homePage.productActionNotificationText()),
                LiteralConstants.addToWishListSuccessMessage(product.getName()));

        // *********Cleaning*********
        if (!containsProduct) {
            WishListPage wishListPage = homePage.gotoWishListPage();
            wishListPage = wishListPage.removeFromWishListByProduct(product);
            homePage = wishListPage.signoutToHomePage();
        } else {
            homePage.signoutToHomePage();
        }

    }
    
 // *********Remove Product To Wish List Success Notification Test*********
  //  @Test(dataProvider = "userProductsProvider")
    public void removeFromListSuccessNotificationTest(IUser user, IProduct product) throws Exception {
        //boolean containsProduct = false;

//        HomePage homePage = Application.get().loadHomePage().loginToHomePage(user);
//        containsProduct = homePage.isWishListContainsProduct(product);
        HomePage homePage = defaultAction(user, product);
        Thread.sleep(1000);

        homePage = homePage.addToWishListByProduct(product);
        Thread.sleep(1000);
        WishListPage wishListPage = homePage.gotoWishListPage(); 

        wishListPage = wishListPage.removeFromWishListByProduct(product);
        Assert.assertEquals(RegexUtils.extractSuccesfullMessage(wishListPage.productActionNotificationText()),
                LiteralConstants.modifyWishListSuccessMessage());

        // *********Cleaning*********
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
    
    // *********Link to Login Page Test*********
     @Test(dataProvider = "userProductsProvider")
    public void linkToLoginPageTest(IProduct product) throws Exception {
     
    }

    // *********Is in Wish List Test*********
    // @Test(dataProvider = "userProductsProvider")
    public void isInListTest(IProduct product) throws Exception {
        HomePage homePage = Application.get().loadHomePage();
        // homePage.addToWishListByProduct(product.getName());
        Thread.sleep(1000);
        WishListPage wishListPage = homePage.gotoWishListPage();
        Thread.sleep(1000);
        // Assert.assertTrue(wishListPage.);
    }

}
