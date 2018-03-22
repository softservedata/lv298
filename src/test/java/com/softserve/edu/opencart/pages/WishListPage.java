package com.softserve.edu.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.tools.RegexUtils;

public class WishListPage extends ARightPanel {

    private final static String WISH_LIST_PAGE_URL = " http://setopencart.epizy.com/index.php?route=account/wishlist";
    
    private final String IN_STOCK = "instock";
    private final String WISH_LIST_LAYOUT_CSS = "table.table.table-bordered.table-hover tbody tr";
    private final String WISH_LIST_IS_EMPTY_CSS = "#content p";
    
    private class ProductComponentInWishList {

        private WebElement productLayout;
        // *********Locators*********
        private String nameCss = "td.text-left a";
        private String modelCss = "td.text-left ~ td.text-left";
        private String stockCss = "td.text-left + td.text-right";
        private String priceClassName = "price";
        private String addToCartCss = ".fa.fa-shopping-cart";
        private String removeFromWishCss = ".fa.fa-times";
      

        // *********Constructor*********
        public ProductComponentInWishList(WebElement productLayout) {
            this.productLayout = productLayout;
            initElements();
        }

        private void initElements() {
            getName();
            getModel();
            getStock();
            getPrice();
            getAddToCart();
            getRemoveFromWishList();
        }

        // *********Name*********
        public WebElement getName() {
            return productLayout.findElement(By.cssSelector(nameCss));
        }

        public String getNameText() {
            return getWebElementText(getName());
        }

        public void clickName() {
            clickWebElement(getName());
        }

        // *********Model*********
        public WebElement getModel() {
            return productLayout.findElement(By.cssSelector(modelCss));
        }

        public String getModelText() {
            return getWebElementText(getModel());
        }

        // *********Stock*********
        public WebElement getStock() {
            return productLayout.findElement(By.cssSelector(stockCss));
        }

        public String getStockText() {
            return getWebElementText(getStock());
        }

        // *********Price*********
        public WebElement getPrice() {
            return productLayout.findElement(By.className(priceClassName));
        }

        public String getPriceText() {
            return getWebElementText(getPrice());
        }

        public double getPriceAmount() {
            return RegexUtils.extractFirstDouble(getPriceText());

        }

        // *********Add To Cart*********
        public WebElement getAddToCart() {
            return productLayout.findElement(By.cssSelector(addToCartCss));
        }

        public void clickAddToCart() {
            clickWebElement(getAddToCart());
        }

        // *********Remove From Wish List*********
        public WebElement getRemoveFromWishList() {
            return productLayout.findElement(By.cssSelector(removeFromWishCss));
        }

        public void clickRemoveFromWish() {
            clickWebElement(getRemoveFromWishList());
        }

    }

    protected List<ProductComponentInWishList> productComponents;
    protected ProductActionNotification productActionNotification;
    private WebElement emptyWishListMessage = null;
    
    // *********Constructor*********
    public WishListPage(WebDriver driver) {
        super(driver);
        initProductComponents();
    }
    
    public static WishListPage load(WebDriver driver) {
        driver.get(WISH_LIST_PAGE_URL);
        return new WishListPage(driver);
    }
    //TODO 
    public boolean isWishListEmpty() {  
        if (wishListAmount() == 0) {
            return true; 
        } else {
            
            return false;
        }
    }
    
    // *********Empty Wish List Message*********
    public WebElement getEmptyWishListMessage() {
        return driver.findElement(By.cssSelector(WISH_LIST_IS_EMPTY_CSS));
    }
    
    public String getEmptyWishListMessageText() {
        return getWebElementText(getEmptyWishListMessage());
    }
    
   

    private void initProductComponents() {
        if (!isWishListEmpty()) {
            productComponents = new ArrayList<ProductComponentInWishList>();
            for (WebElement current : driver.findElements(By.cssSelector(WISH_LIST_LAYOUT_CSS))) {
                productComponents.add(new ProductComponentInWishList(current));
            }
            emptyWishListMessage = null;
        } else {
            getEmptyWishListMessage(); 
             // TODO custom exception
           // throw new RuntimeException("productComponents not found, list is empty");
        }
    }

    // *********Product Component In Wish List*********
    private ProductComponentInWishList getProductComponentByName(String productName) {
        ProductComponentInWishList result = null;
        for (ProductComponentInWishList current : productComponents) {
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

    public List<String> getProductComponentNames() {
        List<String> result = new ArrayList<>();
        for (ProductComponentInWishList current : productComponents) {
            result.add(current.getNameText());
        }
        return result;
    }

    
    public String getModelTextByProductName(String productName) {
        return getProductComponentByName(productName).getModelText();
    }
    
    public boolean isInWishListByProductName(String productName) {
        if(isWishListEmpty()) {
            return false;
        } else {
            return getProductComponentNames().contains(productName);
        }
    }

    public boolean isInStockByProductName(String productName) {
        if (getProductComponentByName(productName).getStockText().toLowerCase().trim().equals(IN_STOCK)) {
            return true;
        } else {
            return false;
        }
    }

    public String getPriceTextByProductName(String productName) {
        return getProductComponentByName(productName).getPriceText();
    }

    public double getPriceAmountByProductName(String productName) {
        return getProductComponentByName(productName).getPriceAmount();
    }

    public void clickAddToCartByProductName(String productName) {
        getProductComponentByName(productName).clickAddToCart();
    }

    public void clickRemoveFromWishByProductName(String productName) {
        getProductComponentByName(productName).clickRemoveFromWish();
    }
    

    // *********Business Logic*********

    public WishListPage removeFromWishListByProduct(String productName) { // TODO
        clickRemoveFromWishByProductName(productName);
        productActionNotification = new ProductActionNotification();
        return new WishListPage(driver);
    }

    public WishListPage AddToCartByProductName(String productName) { // TODO
        clickAddToCartByProductName(productName);
        productActionNotification = new ProductActionNotification();
        return new WishListPage(driver);
    }
}
