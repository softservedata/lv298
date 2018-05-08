package com.softserve.edu.opencart.data;

import com.softserve.edu.opencart.data.taxes.TaxClass;
import com.softserve.edu.opencart.data.taxes.TaxClassRepository;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public enum Currencies {
    EURO("Euro",0.78460002),
    POUND_STERLING("Pound ;Sterling",0.61250001),
    US_DOLLAR("US Dollar",1.0);

    // *********Fields*********
    private String name;
    private double value;

    // *********Constructor*********
    Currencies(final String name, final double value) {
        this.name = name;
        this.value = value;
    }

    // *********Methods*********
    @Override
    public String toString()
    {
        return name;
    }
    public double evaluatePrice(double price, TaxClass tax) {
         DecimalFormat decimalFormat = new DecimalFormat(".##");
         return Double.parseDouble(decimalFormat.format((tax.taxedPrice(price) * value)));
    }

}
