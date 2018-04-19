package com.softserve.edu.opencart.data.products;

import java.util.List;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.tools.CSVReader;
import com.softserve.edu.opencart.tools.DBReader;
import com.softserve.edu.opencart.tools.ExcelReader;

public final class ProductRepository {
	private static final String PRODUCT_BY_NAME = "select name, description, price from product_description join product on product_description.product_id = product.product_id where name = '%s';";
	
    private ProductRepository() {
    }
    
    // TODO Use Builder
//    public static IProduct macBook() {
//        Map<Enum<?>, Double> macBookPrices = new HashMap<>();
//        macBookPrices.put(Currencies.EURO, 560.94);
//        macBookPrices.put(Currencies.POUND_STERLING, 487.62);
//        macBookPrices.put(Currencies.US_DOLLAR, 602.00);
//        return new Product("mac", "MacBook",
//                "Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1",
//                macBookPrices);
//    }

    public static IProduct macBook() {
        return Product.get()
        		.setSearchKey("mac")
        		.setName("MacBook")
        		.setDescription("Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1")
        		//.setPrice(Currencies.EURO, 560.94)
        		.setPrice(Currencies.EURO, 472.33)
        		//.setPrice(Currencies.POUND_STERLING, 487.62)
        		.setPrice(Currencies.POUND_STERLING, 368.73)
        		.setPrice(Currencies.US_DOLLAR, 602.00)
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

    public static List<IProduct> fromDBProductMacBook() {
    	return fromDBProductsByName("MacBook");
    }
    
    public static List<IProduct> fromDBProductsByName(String productName) {
    	return fromDBProducts(String.format(PRODUCT_BY_NAME, productName));
    }

    public static List<IProduct> fromDBProducts(String sqlSelect) {
    	return Product.getByListFromDB(new DBReader(sqlSelect).getAllCells());
    }

}
