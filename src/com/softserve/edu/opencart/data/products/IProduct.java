package com.softserve.edu.opencart.data.products;

import java.util.Map;

import com.softserve.edu.opencart.data.Currencies;

public interface IProduct {

    public String getSearchKey();

    public String getName();

    public String getDescription();

    public Map<Enum<?>, Double> getPrices();

    double getPriceByCurrencyName(Currencies currencyName);
}
