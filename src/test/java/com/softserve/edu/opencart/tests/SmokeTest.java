package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.tools.ListUtils;
import io.qameta.allure.*;
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
    public Object[][] productCurrencyProviderFromDb()  {
        return ListUtils.toMultiArray(ProductRepository.fromDbProducts(), Currencies.EURO);
    }

//    @Test(priority = 5,description = "Smoke Test for Opencart",dataProvider = "productCurrencyProviderFromCsv")
//    @Test(priority = 5,description = "Smoke Test for Opencart",dataProvider = "productCurrencyProviderFromExcel")
    @Test(priority = 5,description = "Smoke Test for Opencart",dataProvider = "productCurrencyProviderFromDb")
    @Description("This test search product and verify it price with test Currency")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Opencart Tests")
    public void smoke5Currency(IProduct product, Currencies currencyName) {
        logger.info("@Test start"
                + " product Name = " + product.getName()
                + " currencyName = " + currencyName.toString());
        SearchPage searchPage = Application.get().loadHomePage()
                .selectCurrency(currencyName)
                .searchByProduct(product);
       // Thread.sleep(1000);
        System.out.println("***" + searchPage.getFeaturedBlock().getProductComponentTexts());
        Assert.assertTrue(searchPage.getFeaturedBlock().getProductComponentTexts()
                .contains(product.getName()));
        Assert.assertEquals(searchPage.getFeaturedBlock().getPriceAmountByProduct(product),
                product.getPriceByCurrencyName(currencyName));
       // Thread.sleep(1000);
        logger.info("@Test done");
    }

}
