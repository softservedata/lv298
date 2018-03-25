package com.softserve.edu.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.products.IProduct;

public final class HomePage extends AHeaderBlock {

    // *********Fields********
    protected FeaturedBlock featuredBlock;

    // *********Constructors*********
    public HomePage(WebDriver driver) {
        super(driver);
        featuredBlock = new FeaturedBlock(driver);
    }

    public HomePage(WebDriver driver, boolean withMessage) {
        super(driver);
        featuredBlock = new FeaturedBlock(driver);
        if (withMessage) {
            productActionNotification = new ProductActionNotification();
        }
    }

    public static HomePage load(WebDriver driver, String url) {
        driver.get(url);
        return new HomePage(driver);
    }

    public static HomePage refresh(WebDriver driver) {
        driver.navigate().refresh();
        return new HomePage(driver);
    }

    // *********Getters********
    public FeaturedBlock getFeaturedBlock() {
        return featuredBlock;
    }

    // *********Business Logic*********

    public HomePage selectCurrency(Currencies currencyName) {
        chooseCurrency(currencyName);
        return new HomePage(driver);
    }

    public HomePage addToWishListByProduct(IProduct product) {
        getFeaturedBlock().clickAddToWishByProduct(product);
        return new HomePage(driver, true);
    }
    
    public HomePage addToCarttByProduct(IProduct product) {
        getFeaturedBlock().clickAddToCartByProduct(product);
        return new HomePage(driver, true);
    }

}
