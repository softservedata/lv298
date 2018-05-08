package com.softserve.edu.opencart.data.products;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.tools.CSVReader;
import com.softserve.edu.opencart.tools.DBReader;
import com.softserve.edu.opencart.tools.ExcelReader;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProductRepository {

    private static volatile ProductRepository instance = null;
    private static volatile  Map<Currencies, Double> prices;

    // *********Constructor*********
    private ProductRepository() {
    }

    public static ProductRepository get() {
        if (instance == null) {
            synchronized (ProductRepository.class) {
                if (instance == null) {
                    instance = new ProductRepository();
                }
            }
        }
        return instance;
    }

    public static IProduct macBook() {
        prices = new HashMap<>();
        prices.put(Currencies.EURO,472.33);
        prices.put(Currencies.POUND_STERLING, 368.73);
        prices.put(Currencies.US_DOLLAR, 602.00);
        return Product.get()
                .setName("MacBook")
                .setSearchKey("mac")
                .setDescription("Intel Core 2 Duo processor Powered by an "
                        + "Intel Core 2 Duo processor at speeds up to "
                        + "2.1")
                .setPrice(prices)
                .buildProduct();
    }

    public static IProduct iPhone() {
        prices = new HashMap<>();
        prices.put(Currencies.EURO,96.66);
        prices.put(Currencies.POUND_STERLING, 75.46);
        prices.put(Currencies.US_DOLLAR, 123.20);
              return Product.get()
                      .setName("iPhone")
                .setSearchKey("iphone")
                .setDescription("iPhone is a revolutionary new mobile phone "
                        + "that allows you to make a call by simply "
                        + "tapping a geoZoneName or number in your address book, a "
                        + "favorites list, or a call log. It also "
                        + "automatically syncs all your contacts from a PC, "
                        + "Mac, or Internet service. And it lets you "
                        + "select and listen to voicemail messages in whatever "
                        + "order you want just like email.")
                .setPrice(prices)
                .buildProduct();
    }


    public static List<IProduct> fromCsvProducts() {
        return fromCsvProducts("products.csv");
    }

    public static List<IProduct> fromCsvProducts(String filename) {
        return Product.getByList(new CSVReader(filename).getAllCells());
    }

    public static List<IProduct> fromExcelProducts() {
        return fromExcelProducts("products.xlsx");
    }

    public static List<IProduct> fromExcelProducts(String filename) {
        return Product.getByList(new ExcelReader(filename).getAllCells());
    }

    public static List<IProduct> fromDbProducts(){
        return fromDbProducts("Select * from products");
    }

    public static List<IProduct> fromDbProducts(String sqlSelect) {
        return Product.getByList(new DBReader(sqlSelect).getAllCells());
    }

    // *********Repository*********
    public IProduct defaultProduct() {
        return macBook();
    }


}
