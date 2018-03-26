package com.softserve.edu.opencart.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.products.IProduct;

public final class HomePage extends AHeaderBlock {

    protected FeaturedBlock featuredBlock;

    public HomePage(WebDriver driver) {
        super(driver);
        featuredBlock = new FeaturedBlock(driver);
    }

    public HomePage(WebDriver driver, boolean withMessage) {//TODO constructor for page with notification
        super(driver);
        featuredBlock = new FeaturedBlock(driver);
        if (withMessage) {
            notificationMessage = new NotificationMessage();
        }
    }

    public static HomePage load(WebDriver driver, String url) {
        driver.get(url);
        return new HomePage(driver);
    }

    public FeaturedBlock getFeaturedBlock() {
        return featuredBlock;
    }
    
    
    // TODO For BrowserTest class. Duplicate Code. Delete 
    public List<String> getProductComponentTexts() {
        return getFeaturedBlock().getProductComponentTexts();
    }
    
    // TODO For BrowserTest class. Duplicate Code. Delete
    public double getPriceAmountByProductName(String productName) {
        return getFeaturedBlock().getPriceAmountByProductName(productName);
    }    
    
    // Business Logic

    public HomePage selectCurrency(Currencies currencyName) {
        chooseCurrency(currencyName);
        return new HomePage(driver);
    }

    public HomePage addToWishListByProduct(String productName) { // TODO product from test
        featuredBlock.clickAddToWishByProductName(productName);
//        NotificationMessage notificationMessage = new NotificationMessage();
        //return this;
        return new HomePage(driver, true);
    }
    
    public HomePage clickAddToCartByProductName(String productName) { // TODO product from test clickAddToCartByProduct
        featuredBlock.clickAddToCartByProductName(productName);
        //return this;
//        NotificationMessage notificationMessage = new NotificationMessage();
        return new HomePage(driver, true);
    }
    
    public HomePage addToWishListByProduct(IProduct product) {
        getFeaturedBlock().clickAddToWishByProduct(product);
        return new HomePage(driver, true);
    }
    
    public HomePage addToCartByProduct(IProduct product) {
        getFeaturedBlock().clickAddToCartByProduct(product);
        return new HomePage(driver, true);
    }
}
