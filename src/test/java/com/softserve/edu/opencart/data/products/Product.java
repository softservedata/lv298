package com.softserve.edu.opencart.data.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softserve.edu.opencart.data.Currencies;

interface ISearchKey {
	IName setSearchKey(String searchKey);
}

interface IName {
	IDescription setName(String name);
}

interface IDescription {
	IPrice setDescription(String description);
}

interface IPrice {
	IBuild setPrice(Currencies currency, Double price);
}

interface IBuild extends IPrice {
	IProduct buildProduct();
}

public class Product implements ISearchKey, IName, IDescription,
								IPrice, IBuild, IProduct {
	private static final int REQUIRED_COLUMN_NUMBER = 3;

    private String searchKey;
    private String name;
    private String description;
    private Map<Enum<?>, Double> prices;

    private Product() {
    	this.prices = new HashMap<Enum<?>, Double>();
    }
    
    // TODO Develop Builder
    //public Product(String searchKey, String name, String description, Map<Enum<?>, Double> prices) {
    //    this.searchKey = searchKey;
    //    this.name = name;
    //    this.description = description;
    //    this.prices = prices;
    //}

    public static List<IProduct> getByList(List<List<String>> rows) {
    	List<IProduct> result = new ArrayList<>();
    	String node;
    	for (List<String> currentRow : rows) {
    		node = currentRow.get(REQUIRED_COLUMN_NUMBER);
    	}
    	return result;
    }

    public static ISearchKey get() {
    	return new Product();
    }
    
    // setters

    public IName setSearchKey(String searchKey) {
        this.searchKey = searchKey;
        return this;
    }

    public IDescription setName(String name) {
        this.name = name;
        return this;
    }

    public IPrice setDescription(String description) {
        this.description = description;
        return this;
    }

    public IBuild setPrice(Currencies currency, Double price) {
    	prices.put(currency, price);
        return this;
    }
    
    //public void setPrices(Map<Enum<?>, Double> prices) {
    //    this.prices = prices;
    //}

    public IProduct buildProduct() {
    	return this;
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
