package com.softserve.edu.opencart.data.products;

import com.softserve.edu.opencart.data.Currencies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    IProductBuild setPrice(Currencies currency, Double price);
}

interface IProductBuild  extends  IPrices{
    IProduct  buildProduct();
}

public final class Product implements ISearchKey, IName, IDescription, IPrices,
                                                    IProductBuild, IProduct {

    private static final int REQUIRED_COLUMN_NUMBER = 3;
    // *********Product Properties*********
    private String searchKey;
    private String name;
    private String description;
    private Map<Enum<Currencies>, Double> prices;

    // *********Constructor*********
    private Product() {
        this.prices = new HashMap<Enum<Currencies>, Double>();
    }

    public static List<IProduct> getByList(List<List<String>> rows) {
            	List<IProduct> result = new ArrayList<>();
            	String node;
            	for (List<String> currentRow : rows) {
                		node = currentRow.get(REQUIRED_COLUMN_NUMBER);
                	}
           	return result;
    }

    public static ISearchKey get()
    {
        return new Product();
    }

    // *********Setters*********
    public IName setSearchKey(final String searchKey) {
        this.searchKey = searchKey;
        return this;
    }

    public IDescription setName(final String name) {
        this.name = name;
        return this;
    }

    public IPrices setDescription(final String description) {
        this.description = description;
        return this;
    }

    public IProductBuild setPrice(final Currencies currency, Double price) {
        prices.put(currency, price);
        return this;
    }

    public IProduct buildProduct() {
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

    public Map<Enum<Currencies>, Double> getPrices() {
        return prices;
    }

    public double getPriceByCurrencyName(final Currencies currencyName) {
        return getPrices().get(currencyName);
    }

}
