package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.tools.RegexUtils;

public class ProductComponent {

    private WebElement productLayout;
    //
    // private WebElement name;
    // private WebElement price;
    // private WebElement addToCart;
    // private WebElement addToWish;

    public ProductComponent(WebElement productLayout) {
        this.productLayout = productLayout;
        //
        // Verify Web Elements
        verifyWebElements();
    }

    private void verifyWebElements() {
        // TODO Check, if Web Elements Exist
        getName();
        getPrice();
        getAddToCart();
        getAddToWish();
    }

    // name
    public WebElement getName() {
        // return name;
        return productLayout.findElement(By.cssSelector("h4 a"));
    }

    public String getNameText() {
        return getName().getText();
    }

    public void clickName() {
        getName().click();
    }

    // price
    public WebElement getPrice() {
        // return price;
        return productLayout.findElement(By.cssSelector(".price"));
    }

    public String getPriceText() {
        return getPrice().getText();
    }

    // TODO
    public double getPriceAmount() {
        return RegexUtils.extractFirstDouble(getPriceText());
    }

    // addToCart
    public WebElement getAddToCart() {
        return productLayout.findElement(By.cssSelector(".fa.fa-shopping-cart"));
    }

    public void clickAddToCart() {
        getAddToCart().click();
//        NotificationMessage notificationMessage = new NotificationMessage();
    }

    // addToWish
    public WebElement getAddToWish() {
        // return currency;
        return productLayout.findElement(By.cssSelector(".fa.fa-heart"));
    }

    public void clickAddToWish() {
        getAddToWish().click();
    }

}
