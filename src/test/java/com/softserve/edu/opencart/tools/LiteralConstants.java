package com.softserve.edu.opencart.tools;

public final class LiteralConstants {

    private LiteralConstants() {

    }

    public static String addToCartSuccessMessage(String productName) {
        return "Success: You have added " + productName + " to your shopping cart!";
    }
    
    public static String addToWishListSuccessMessage(String productName) {
        return "Success: You have added " + productName + " to your wish list!";
    }
    
    public static String addToCompareSuccessMessage(String productName) {
        return "Success: You have added " + productName + " to your product comparison!";
    }
    
    public static String modifyCompareSuccessMessage(String productName) {
        return "Success: You have modified your product comparison!";
    }
    
    public static String modifyWishListSuccessMessage(String productName) {
        return "Success: You have modified your wish list!";
    }

}
