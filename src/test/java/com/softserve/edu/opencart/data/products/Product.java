package com.softserve.edu.opencart.data.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.tools.RegexUtils;

interface ISearchKey {
	IName setSearchKey(String searchKey);
}

interface IName {
	IDescription setName(String name);
}

interface IDescription {
	IPrice setDescription(String description);
	IPartialProduct setDescriptionNext(String description);
}

interface IPartialProduct {
	IBuild setPrice(Currencies currency, Double price);
}

interface IPrice extends IPartialProduct {
	// IBuild setPrice(Currencies currency, Double price);
}

interface IBuild extends IPrice {
	IProduct buildProduct();
}

public class Product implements ISearchKey, IName, IDescription, IPartialProduct,
								IPrice, IBuild, IProduct {
	private static final int REQUIRED_COLUMN_SEARCH_KEY = 0;
	private static final int REQUIRED_COLUMN_NAME = 1;
	private static final int REQUIRED_COLUMN_DESCRIPTION = 2;
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
    	Map<Integer, Currencies> currencies = new HashMap<>();
    	String node;
    	for (List<String> currentRow : rows) {
    		node = currentRow.get(REQUIRED_COLUMN_NUMBER);
    		if (RegexUtils.isDoubleMatches(node)) {
    			IPartialProduct partialProduct = Product.get()
        		.setSearchKey(currentRow.get(REQUIRED_COLUMN_SEARCH_KEY))
        		.setName(currentRow.get(REQUIRED_COLUMN_NAME))
        		.setDescriptionNext(currentRow.get(REQUIRED_COLUMN_DESCRIPTION));
    			//
    			for (int i = REQUIRED_COLUMN_NUMBER; i < currentRow.size(); i++) {
    				partialProduct = partialProduct.setPrice(currencies.get(i),
    						RegexUtils.extractFirstDouble(currentRow.get(i)));  
    			}
    			result.add(((IBuild)partialProduct).buildProduct());
    		} else {
				for (int i = REQUIRED_COLUMN_NUMBER; i < currentRow.size(); i++) {
    				for (Currencies currency : Currencies.values()) {
    					if (currentRow.get(i).toLowerCase().equals(
    							currency.name().toLowerCase())) {
    						currencies.put(i, currency);
    						continue;
    					}
    				}
    			}
    		}
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

	public IPartialProduct setDescriptionNext(String description) {
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
