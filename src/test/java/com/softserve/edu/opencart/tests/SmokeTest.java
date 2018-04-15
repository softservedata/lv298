package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.tools.ListUtils;
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

import java.sql.SQLException;

public class SmokeTest extends TestRunner {

    String URL = "jdbc:mysql://192.168.0.108:3306/opencart";
    String username = "svehetc";
    String ps = "Lv298_Set";
  
    //@Test(dataProvider = "productsProvider")
    public void smoke3TestRunner(IProduct product) throws Exception {
        SearchPage searchPage = Application.get().loadHomePage() 
                .searchByProduct(product);
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

        Assert.assertEquals(myAccountPage.getMyAccountLabelText(),
                MyAccountPage.MY_ACCOUNT_LABEL_TEXT);
        HomePage homePage = myAccountPage.signoutToHomePage();
        Assert.assertFalse(homePage.isLogged());
    }
    
    @DataProvider
    public Object[][] productCurrencyProvider() {
        return new Object[][] {
            { ProductRepository.macBook(),  Currencies.EURO },
            //{ ProductRepository.macBook(),  Currencies.POUND_STERLING }
        };
    }

    @DataProvider//(parallel = true)
    public Object[][] productCurrencyProviderFromCsv() {
        return ListUtils.toMultiArray(ProductRepository.fromCsvProducts(), Currencies.POUND_STERLING);
    }

    @DataProvider//(parallel = true)
    public Object[][] productCurrencyProviderFromExcel() {
        return ListUtils.toMultiArray(ProductRepository.fromExcelProducts(), Currencies.EURO);
    }

    @DataProvider//(parallel = true)
    public Object[][] productCurrencyProviderFromDb() throws SQLException {
        return ListUtils.toMultiArray(ProductRepository.fromDbProducts(URL,username,ps), Currencies.EURO);
    }

//    @Test(dataProvider = "productCurrencyProviderFromCsv")
//    @Test(dataProvider = "productCurrencyProviderFromExcel")
    @Test(dataProvider = "productCurrencyProviderFromDb")

    public void smoke5Currency(IProduct product, Currencies currencyName) throws Exception {
        logger.info("@Test start"
                + " product Name = " + product.getName()
                + " currencyName = " + currencyName.toString());
        SearchPage searchPage = Application.get().loadHomePage()
                .selectCurrency(currencyName)
                .searchByProduct(product);
        Thread.sleep(1000);
        System.out.println("***" + searchPage.getFeaturedBlock().getProductComponentTexts());
        Assert.assertTrue(searchPage.getFeaturedBlock().getProductComponentTexts()
                .contains(product.getName()));
        Assert.assertEquals(searchPage.getFeaturedBlock().getPriceAmountByProduct(product),
                product.getPriceByCurrencyName(currencyName));
        Thread.sleep(1000);
        logger.info("@Test done");
    }

}
