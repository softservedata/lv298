package com.softserve.edu.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.products.IProduct;
import com.softserve.edu.opencart.tools.RegexUtils;

public class WishListPage extends ARightPanel {
    private class ProductComponentInWishList {

        private WebElement productLayout;
        // *********ProductComponentInWishList Locators*********
        private final String ANCH_NAME_CSS = "td.text-left a";
        private final String TXT_MODEL_CSS = "td.text-left ~ td.text-left";
        private final String TXT_STOCK_CSS = "td.text-left + td.text-right";
        private final String P_PRICE_CLASS_NAME = "price";
        private final String BTN_ADD_TO_CART_CSS = ".fa.fa-shopping-cart";
        private final String BTN_REMOVE_FROM_LIST_CSS = ".fa.fa-times";

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
            return productLayout.findElement(By.cssSelector(ANCH_NAME_CSS));
        }

        public String getNameText() {
            return getWebElementText(getName());
        }

        public void clickName() {
            clickWebElement(getName());
        }

        // *********Model*********
        public WebElement getModel() {
            return productLayout.findElement(By.cssSelector(TXT_MODEL_CSS));
        }

        public String getModelText() {
            return getWebElementText(getModel());
        }

        // *********Stock*********
        public WebElement getStock() {
            return productLayout.findElement(By.cssSelector(TXT_STOCK_CSS));
        }

        public String getStockText() {
            return getWebElementText(getStock());
        }

        // *********Price*********
        public WebElement getPrice() {
            return productLayout.findElement(By.className(P_PRICE_CLASS_NAME));
        }

        public String getPriceText() {
            return getWebElementText(getPrice());
        }

        public double getPriceAmount() {
            return RegexUtils.extractFirstDouble(getPriceText());

        }

        // *********Add To Cart*********
        public WebElement getAddToCart() {
            return productLayout.findElement(By.cssSelector(BTN_ADD_TO_CART_CSS));
        }

        public void clickAddToCart() {
            clickWebElement(getAddToCart());
        }

        // *********Remove From Wish List*********
        public WebElement getRemoveFromWishList() {
            return productLayout.findElement(By.cssSelector(BTN_REMOVE_FROM_LIST_CSS));
        }

        public void clickRemoveFromWish() {
            clickWebElement(getRemoveFromWishList());
        }

    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    // *********WishListPage Fields*********
    public final String URL = "http://setopencart.epizy.com/index.php?route=account/wishlist";
    private final String IN_STOCK = "instock";
    
    protected List<ProductComponentInWishList> productComponents;
    
    // *********WishListPage Locators*********
    private final String DIV_WISH_LIST_LAYOUT_CSS = ".table-responsive tbody tr";
    private final String P_WISH_LIST_IS_EMPTY_CSS = "#content p";
    
    // *********Constructors*********
    public WishListPage(WebDriver driver) {
        super(driver);
        initProductComponents();
    }
    
    public WishListPage(WebDriver driver, boolean withMessage) {
        super(driver);
        initProductComponents();
        if (withMessage) {
            productActionNotification = new ProductActionNotification();
        }
    }
    
    // *********Empty Wish List Message*********
    public WebElement getEmptyWishListMessage() {
        return driver.findElement(By.cssSelector(P_WISH_LIST_IS_EMPTY_CSS));
    }
    
    public String getEmptyWishListMessageText() {
        return getWebElementText(getEmptyWishListMessage());
    }
   
    public boolean isWishListEmpty() {  
        if (wishListAmount() == 0) {
            return true; 
        } else {
            return false;
        }
    }
    
    public boolean isProductInList(IProduct product) {  
        if (isWishListEmpty()) {
            return false; 
        } else if (getProductComponentNames().contains(product.getName())){
            
            return true;
        } else {
            return false;
        }
    }

    private void initProductComponents() {
        if (!isWishListEmpty()) {
            productComponents = new ArrayList<ProductComponentInWishList>();
            for (WebElement current : driver.findElements(By.cssSelector(DIV_WISH_LIST_LAYOUT_CSS))) {
                productComponents.add(new ProductComponentInWishList(current));
            }
        } else {
            getEmptyWishListMessage(); 
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

    public String getModelTextByProduct(IProduct product) {
        return getProductComponentByName(product.getName()).getModelText();
    }
    
    public boolean isInWishListByProduct(IProduct product) {
        if(isWishListEmpty()) {
            return false;
        } else {
            return getProductComponentNames().contains(product.getName());
        }
    }

    public boolean isInStockByProduct(IProduct product) {
        if (getProductComponentByName(product.getName()).getStockText().toLowerCase().trim().equals(IN_STOCK)) {
            return true;
        } else {
            return false;
        }
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

    public void clickRemoveFromWishByProduct(IProduct product) {
        getProductComponentByName(product.getName()).clickRemoveFromWish();
    }

    // *********Business Logic*********
    public WishListPage removeFromWishListByProduct(IProduct product) { 
        clickRemoveFromWishByProduct(product);
        return new WishListPage(driver, true);
    }

    public WishListPage AddToCartByProduct(IProduct product) { 
        clickAddToCartByProduct(product);
        return new WishListPage(driver, true);
    }
}
