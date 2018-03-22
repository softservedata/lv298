package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.tools.RegexUtils;

public class ProductComponent {

    private WebElement productLayout;

    // *********Locators*********
    private String nameCss = "h4 a";
    private String priceClassName = "price";
    private String addToCartCss = ".fa.fa-shopping-cart";
    private String addToWishCss = ".fa.fa-heart";

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
        return productLayout.findElement(By.cssSelector(nameCss));
    }

    public String getNameText() {
        return getName().getText();
    }

    public void clickName() {
        getName().click();
    }

    // *********Price*********
    public WebElement getPrice() {
        return productLayout.findElement(By.className(priceClassName));
    }

    public String getPriceText() {
        return getPrice().getText();
    }

    public double getPriceAmount() {
        return RegexUtils.extractFirstDouble(getPriceText());
    }

    // *********Add To Cart*********
    public WebElement getAddToCart() {
        return productLayout.findElement(By.cssSelector(addToCartCss));
    }

    public void clickAddToCart() {
        getAddToCart().click();
    }

    // *********Add To Wish*********
    public WebElement getAddToWish() {
        return productLayout.findElement(By.cssSelector(addToWishCss));
    }

    public void clickAddToWish() {
        getAddToWish().click();
    }

}
