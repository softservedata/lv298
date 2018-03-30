package com.softserve.edu.opencart.data;

public enum Currencies {
    EURO("Euro"),
    POUND_STERLING("Pound Sterling"),
    US_DOLLAR("US Dollar");
    
    // *********Fields*********
    private String name;
    
    // *********Constructor*********
    private Currencies(String name) {
        this.name = name;
    }
    
    // *********Methods*********
    @Override
    public String toString() {
        return name;
    }

}
