package com.softserve.edu.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.products.IProduct;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeaturedBlock {

    private WebDriver driver;
    protected List<ProductComponent> productComponents;

    // *********Locators*********
    private static final String DIV_PRODUCT_LAYOUT_CLASS_NAME = "product-layout";
    
    // *********Constructor*********
    public FeaturedBlock(WebDriver driver) {    
        this.driver = driver;
        initProductComponents();
    }
    
    private void initProductComponents() {
        productComponents = new ArrayList<>();
        for (WebElement current : driver.findElements(By.className(DIV_PRODUCT_LAYOUT_CLASS_NAME))) {
            productComponents.add(new ProductComponent(current));
        }
    }

    // *********Product Components*********
    public List<ProductComponent> getProductComponents() {
        return productComponents;
    }

    public ProductComponent getProductComponentByName(String productName) {
        ProductComponent result = null;
        for (ProductComponent current : getProductComponents()) {
            if (current.getNameText().toLowerCase().trim().equals(productName.toLowerCase().trim())) {
                result = current;
                break;
            }
        }
        if (result == null) {
            // TODO Develop Custom Exception
            throw new RuntimeException("productName " + productName + " not found");
        }
        return result;
    }

    public List<String> getProductComponentTexts() {
        List<String> result = new ArrayList<>();
        for (ProductComponent current : getProductComponents()) {
            result.add(current.getNameText());
        }
        return result;
    }

    public String getPriceTextByProduct(IProduct product) {
        return getProductComponentByName(product.getName()).getPriceText();
    }

    public double getPriceAmountByProduct(IProduct product) {
        return getProductComponentByName(product.getName()).getPriceAmount();
    }

    public void clickAddToCartByProduct(IProduct product) {
        getProductComponentByName(product.getName()).clickAddToCart();
    }

    public void clickAddToWishByProduct(IProduct product) {
        getProductComponentByName(product.getName()).clickAddToWish();
    }

}
