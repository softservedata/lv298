package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.tools.RegexUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductComponent {
    
    // *********Fields*********
    private WebElement productLayout;
    
    // *********Locators*********
    private static final String ANCH_PRODUCT_NAME_CSS = "div.caption a";
    private static final String P_PRICE_CLASS_NAME = "price";
    private static final String BTN_ADD_TO_CART_CSS = ".fa.fa-shopping-cart";
    private static final String BTN_ADD_TO_WISH_CSS = ".fa.fa-heart";

    // *********Constructor*********
    public ProductComponent(WebElement productLayout) {
        this.productLayout = productLayout;    
        initElements();    
    }


    private void initElements() {
        getName();
        getPrice();
        getAddToCart();
        getAddToWish();
    }

    // *********Name*********
    public WebElement getName() {       
       return productLayout.findElement(By.cssSelector(ANCH_PRODUCT_NAME_CSS));
    }

    public String getNameText() {
        return getName().getText();
    }

    public void clickName() {
        getName().click();
    }

    // *********Price*********
    public WebElement getPrice() {
        return productLayout.findElement(By.className(P_PRICE_CLASS_NAME));
    }

    public String getPriceText() {
        return getPrice().getText();
    }

    public double getPriceAmount() {
        return RegexUtils.extractFirstDouble(getPriceText());
    }

    // *********Add To Cart*********
    public WebElement getAddToCart() {
        return productLayout.findElement(By.cssSelector(BTN_ADD_TO_CART_CSS));
    }

    public void clickAddToCart() {
        getAddToCart().click();
    }

    // *********Add To Wish*********
    public WebElement getAddToWish() {
        return productLayout.findElement(By.cssSelector(BTN_ADD_TO_WISH_CSS));
    }

    public void clickAddToWish()  {
        getAddToWish().click();

    }

}
