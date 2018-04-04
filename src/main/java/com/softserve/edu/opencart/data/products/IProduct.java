package com.softserve.edu.opencart.data.products;

import com.softserve.edu.opencart.data.Currencies;

import java.util.Map;

public interface IProduct {

    String getSearchKey();

    String getName();

    String getDescription();

    Map<Enum<Currencies>, Double> getPrices();

    double getPriceByCurrencyName(Currencies currencyName);
}
