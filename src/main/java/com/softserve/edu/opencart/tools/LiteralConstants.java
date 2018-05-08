package com.softserve.edu.opencart.tools;

public final class LiteralConstants {
    public static final String MODIFY_WISH_LIST_SUCCESS_MESSAGE = "Success: You have modified your wish list!";
    public static final String MODIFY_COMPARE_SUCCESS_MESSAGE ="Success: You have modified your product comparison!";
    private static final String SUCCESS = "Success: You have added ";
    private LiteralConstants() {

    }

    public static String addToCartSuccessMessage(String productName) {
        return SUCCESS + productName + " to your shopping cart!";
    }
    
    public static String addToWishListSuccessMessage(String productName) {
        return SUCCESS + productName + " to your wish list!";
    }
    
    public static String addToCompareSuccessMessage(String productName) {
        return SUCCESS + productName + " to your product comparison!";
    }

}
