package com.softserve.edu.opencart.data.products;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.taxes.TaxClassRepository;
import com.softserve.edu.opencart.tools.CSVReader;
import com.softserve.edu.opencart.tools.RegexUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//*********Builder Pattern*********

interface IName {
    ISearchKey setName(String name);
}

interface ISearchKey {
    IDescription setSearchKey(String searchKey);
}

interface IDescription {
    IPrices setDescription(String description);
}

interface IPrices {
    IProductBuild setPrice(Map<Currencies,Double> prices);
}

interface IProductBuild {
    IProduct  buildProduct();
}

public final class Product implements ISearchKey, IName, IDescription, IPrices,
                                                    IProductBuild, IProduct {
//    private static final String SEARCH_KEY_HEADER = "searchKey";
    private static final String NAME_HEADER = "name";
    private static final String DESCRIPTION_HEADER = "description";
    private static final String PRICE_HEADER = "price";

    // *********Product Properties*********
    private String searchKey;
    private String name;
    private String description;
    private Map<Currencies, Double> prices;

    // *********Constructor*********
    private Product() {
    }

    public static List<IProduct> getByList(List<List<String>> rows) {
        List<IProduct> result = new ArrayList<>();
        Map<String, Integer> headers = new HashMap<>();
        for (List<String> currentRow : rows) {
            Map<Currencies, Double> productPrices = new HashMap<>();
            if (!RegexUtils.isWords(currentRow.toString())) {
                //fill prices map
                    for (Currencies currency : Currencies.values()) {
                        //TODO delete
                        /*if (headers.containsKey(currency.name())){
                            productPrices.put(currency, RegexUtils.extractFirstDouble(currentRow.get(headers.get(currency.name()))));
                        }*/
                        productPrices.put(currency, currency.evaluatePrice(
                                Double.parseDouble(currentRow.get(headers.get(PRICE_HEADER))),
                                TaxClassRepository.get().taxebleGoods()));
                    }
                //build product
                result.add(
                        Product.get()
                        .setName(currentRow.get(headers.get(NAME_HEADER)))
                        .setSearchKey(currentRow.get(headers.get(NAME_HEADER)))
                        .setDescription(currentRow.get(headers.get(DESCRIPTION_HEADER)))
                        .setPrice(productPrices)
                        .buildProduct()
                );
            } else {
                //fill columns header map
                for (int i = 0; i < currentRow.size(); i++) {
                    headers.put(currentRow.get(i), i);
                }
            }
        }
        //TODO delete
//        System.out.println("headers= "+ headers);
//        System.out.println("prices = "+ prices);
//        System.out.println("result = "+ result.toString());
        return result;
    }

    public static IName get() {
        return new Product();
    }

    // *********Setters*********

    public ISearchKey setName(final String name) {
        this.name = name;
        return this;
    }

    public IDescription setSearchKey(final String searchKey) {
        this.searchKey = searchKey.substring(0,3).toLowerCase();
        return this;
    }

    public IPrices setDescription(final String description) {
        this.description = description;
        return this;
    }

    public IProductBuild setPrice(final Map<Currencies,Double> prices) {
        this.prices = prices;
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

    public Map<Currencies, Double> getPrices() {
        return prices;
    }

    public double getPriceByCurrencyName(final Currencies currencyName) {
        return getPrices().get(currencyName);
    }

    @Override
    public String toString(){
        return String.format("%s; %s; %s; %s", getName(), getSearchKey(), getPrices(), getDescription());
    }

    //TODO delete
    public static void main(String[] args) {
        getByList(new CSVReader("products.csv").getAllCells());
    }
}
