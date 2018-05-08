package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.data.taxes.ITaxRate;
import com.softserve.edu.opencart.data.taxes.TaxClass;
import com.softserve.edu.opencart.data.taxes.TaxClassRepository;
import com.softserve.edu.opencart.data.taxes.TaxRateRepository;
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

import javax.swing.plaf.PanelUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SmokeTest extends TestRunner {

    private final String queryTest1WithID= "SELECT product_description.product_id," +
            "product_description.name,product.price,product_description" +
            ".description \n"+
            "FROM product \n"+
            "JOIN product_description ON product.product_id = " +
            "product_description.product_id\n"+
            "WHERE product.product_id < 30;";

    private final String taxqueryTest1WithID="SELECT  tax_rate.name, tax_rate" +
            ".rate, tax_rate.type,  geo_zone.name AS geoZone\n" +
            "FROM tax_rate \n" +
            "JOIN geo_zone ON geo_zone.geo_zone_id = tax_rate.geo_zone_id;";

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
//        return ListUtils.toMultiArray(ProductRepository.fromDbProducts(), Currencies.EURO);
        return ListUtils.toMultiArray(ProductRepository.fromDbProducts(queryTest1WithID), Currencies.EURO);
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

    @DataProvider//(parallel = true)
    public Object[][] taxRateProviderFromDb()  {
//        return ListUtils.toMultiArray(ProductRepository.fromDbProducts(queryTest1WithID));
        return ListUtils.toMultiArray(TaxRateRepository.fromDbTaxRates(taxqueryTest1WithID));
    }

    @DataProvider//(parallel = true)
    public Object[][] TaxClassProviderFromDb()  {
        return ListUtils.toMultiArray(TaxClassRepository.fromDbTaxClass());
    }

    @Test(dataProvider = "taxRateProviderFromDb")
    public void test1(ITaxRate taxRate){
//    public void test1(IProduct product){

        System.out.println(taxRate);
    }

    @Test(dataProvider = "TaxClassProviderFromDb")
    public void test2(TaxClass taxClass){
//    public void test1(IProduct product){

        System.out.println(taxClass);
    }

}
