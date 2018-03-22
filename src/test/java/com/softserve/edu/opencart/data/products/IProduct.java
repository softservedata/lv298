package com.softserve.edu.opencart.data.products;

import java.util.Map;

import com.softserve.edu.opencart.data.Currencies;

public interface IProduct {

    String getSearchKey();

    String getName();

    String getDescription();

    Map<Enum<?>, Double> getPrices();

    double getPriceByCurrencyName(Currencies currencyName);
}
