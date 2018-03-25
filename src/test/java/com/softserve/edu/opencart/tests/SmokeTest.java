package com.softserve.edu.opencart.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.products.IProduct;
import com.softserve.edu.opencart.data.products.ProductRepository;
import com.softserve.edu.opencart.data.users.IUser;
import com.softserve.edu.opencart.data.users.UserRepository;
import com.softserve.edu.opencart.pages.Application;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.MyAccountPage;
import com.softserve.edu.opencart.pages.SearchPage;

public class SmokeTest extends TestRunner {


  
    //@Test(dataProvider = "productsProvider")
    public void smoke3TestRunner(IProduct product) throws Exception {
        SearchPage searchPage = Application.get().loadHomePage() 
                .searchByProduct(product);
        Thread.sleep(4000);
        System.out.println("***" + searchPage.getFeaturedBlock().getProductComponentTexts());
        Assert.assertTrue(searchPage.getFeaturedBlock().getProductComponentTexts()
                .contains(product.getName()));
    }

    @DataProvider
    public Object[][] usersProvider() {
        return new Object[][] { { UserRepository.get().customer() } };
    }

    //@Test(dataProvider = "usersProvider")
    public void smoke4Login(IUser user) throws Exception {
        MyAccountPage myAccountPage = Application.get()
                .loadHomePage() 
                .gotoLoginPage()
                .successLogin(user);
        Thread.sleep(4000);
         
        Assert.assertEquals(myAccountPage.getMyAccountLabelText(),
                MyAccountPage.MY_ACCOUNT_LABEL_TEXT);
        HomePage homePage = myAccountPage.signoutToHomePage();
        Thread.sleep(4000);
        Assert.assertFalse(homePage.isLogged());
    }
    
    @DataProvider
    public Object[][] productCurrencyProvider() {
        return new Object[][] {
            { ProductRepository.macBook(),  Currencies.EURO },
            //{ ProductRepository.macBook(),  Currencies.POUND_STERLING }
        };
    }

    @Test(dataProvider = "productCurrencyProvider")
    public void smoke5Currency(IProduct product, Currencies currencyName) throws Exception {
        SearchPage searchPage = Application.get().loadHomePage() 
                .selectCurrency(currencyName)
                .searchByProduct(product);
        Thread.sleep(4000);
        System.out.println("***" + searchPage.getFeaturedBlock().getProductComponentTexts());
        Assert.assertTrue(searchPage.getFeaturedBlock().getProductComponentTexts()
                .contains(product.getName()));
        Assert.assertEquals(searchPage.getFeaturedBlock().getPriceAmountByProduct(product),
                product.getPriceByCurrencyName(currencyName));
        Thread.sleep(4000);
    }

}
