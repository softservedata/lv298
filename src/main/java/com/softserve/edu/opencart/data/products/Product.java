package com.softserve.edu.opencart.data.products;

import java.util.Map;

import com.softserve.edu.opencart.data.Currencies;

//*********Builder Pattern*********
interface ISearchKey {
    IName setSearchKey(String searchKey);
}

interface IName {
    IDescription setName(String name);
}

interface IDescription {
    IPrices setDescription(String description);
}

interface IPrices {
    IProductBuild setPrices(Map<Enum<?>, Double> prices);
}

interface IProductBuild {
    IProduct build();
}

public class Product implements ISearchKey, IName, IDescription, IPrices, IProductBuild,
                                IProduct {

    // *********Product Properties*********
    private String searchKey;
    private String name;
    private String description;
    private Map<Enum<?>, Double> prices;

    // *********Constructor*********
    private Product() {
    }

    public static ISearchKey get() {
        return new Product();
    }

    // *********Setters*********
    public IName setSearchKey(String searchKey) {
        this.searchKey = searchKey;
        return this;
    }

    public IDescription setName(String name) {
        this.name = name;
        return this;
    }

    public IPrices setDescription(String description) {
        this.description = description;
        return this;
    }

    public IProductBuild setPrices(Map<Enum<?>, Double> prices) {
        this.prices = prices;
        return this;
    }

    public IProduct build() {
        return this;
    }

    // *********Getters*********
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
