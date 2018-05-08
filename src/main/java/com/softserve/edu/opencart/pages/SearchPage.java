package com.softserve.edu.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.products.IProduct;

public class SearchPage extends AAdressBar {

    // *********Fields********
    protected FeaturedBlock featuredBlock;
    
    // *********Constructor*********
    public SearchPage(WebDriver driver) {
        super(driver);
        featuredBlock = new FeaturedBlock(driver);
    }
    
    public SearchPage(WebDriver driver, boolean withMessage) {
        super(driver);
        featuredBlock = new FeaturedBlock(driver);
        if (withMessage) {
            productActionNotification = new ProductActionNotification();
        }
    }

    // *********Getters********
    public FeaturedBlock getFeaturedBlock() {
        return featuredBlock;
    }

    // *********Business Logic*********
    public SearchPage selectCurrency(Currencies currencyName) {
        chooseCurrency(currencyName);
        return new SearchPage(driver);
    }

    public SearchPage addToWishListByProduct(IProduct product) {
        getFeaturedBlock().clickAddToWishByProduct(product);
        return new SearchPage(driver,true);
    }

}
