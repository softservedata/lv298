package com.softserve.edu.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.data.Currencies;

public class SearchPage extends AAdressBar {

    protected FeaturedBlock featuredBlock;
    // *********Web Elements*********

    //

    // *********Constructor*********
    public SearchPage(WebDriver driver) {
        super(driver);
        featuredBlock = new FeaturedBlock(driver);
    }

    public FeaturedBlock getFeaturedBlock() {
        return featuredBlock;
    }

    // *********Business Logic*********

    public SearchPage selectCurrency(Currencies currencyName) {
        chooseCurrency(currencyName);
        return new SearchPage(driver);
    }

    public SearchPage addToWishListByProduct(String productName) { // TODO
        featuredBlock.clickAddToWishByProductName(productName);
        return new SearchPage(driver);
    }

}
