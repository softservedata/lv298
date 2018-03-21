package com.softserve.edu.opencart.data.products;

import java.util.Map;

import com.softserve.edu.opencart.data.Currencies;

public class Product implements IProduct {

    private String searchKey;
    private String name;
    private String description;
    private Map<Enum<?>, Double> prices;

    // TODO Develop Builder

    public Product(String searchKey, String name, String description, Map<Enum<?>, Double> prices) {
        this.searchKey = searchKey;
        this.name = name;
        this.description = description;
        this.prices = prices;
    }

    // setters

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrices(Map<Enum<?>, Double> prices) {
        this.prices = prices;
    }

    // getters

    public String getSearchKey() {
        return searchKey;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<Enum<?>, Double> getPrices() {
        return prices;
    }

    public double getPriceByCurrencyName(Currencies currencyName) {
        return getPrices().get(currencyName);
    }

}
