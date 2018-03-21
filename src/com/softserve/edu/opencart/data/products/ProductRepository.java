package com.softserve.edu.opencart.data.products;

import java.util.HashMap;
import java.util.Map;

import com.softserve.edu.opencart.data.Currencies;

public final class ProductRepository {

    private ProductRepository() {
    }
    
    // TODO Use Builder
    public static IProduct macBook() {
        Map<Enum<?>, Double> macBookPrices = new HashMap<>();
        macBookPrices.put(Currencies.EURO, 560.94);
        macBookPrices.put(Currencies.POUND_STERLING, 487.62);
        macBookPrices.put(Currencies.US_DOLLAR, 602.00);
        return new Product("mac", "MacBook",
                "Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1",
                macBookPrices);
    }

}
