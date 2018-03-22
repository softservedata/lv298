package com.softserve.edu.opencart.data.products;

import java.util.HashMap;
import java.util.Map;

import com.softserve.edu.opencart.data.Currencies;

public final class ProductRepository {
    
    private static volatile ProductRepository instance = null;
    
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
    
    // *********Repository*********
    public IProduct defaultProduct() {
        return macBook();
    }
    
    public static IProduct macBook() {
        Map<Enum<?>, Double> macBookPrices = new HashMap<>();
        macBookPrices.put(Currencies.EURO, 472.33);
        macBookPrices.put(Currencies.POUND_STERLING, 368.73);
        macBookPrices.put(Currencies.US_DOLLAR, 602.00);
        return Product.get()
                .setSearchKey("mac")
                .setName("MacBook")
                .setDescription("Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1")
                .setPrices(macBookPrices)
                .build();              
    }
    
    public static IProduct iPhone() {
        Map<Enum<?>, Double> iPhonePrices = new HashMap<>();
        iPhonePrices.put(Currencies.EURO, 96.66);
        iPhonePrices.put(Currencies.POUND_STERLING, 75.46);
        iPhonePrices.put(Currencies.US_DOLLAR, 123.20);
        return Product.get()
                .setSearchKey("iphone")
                .setName("iPhone")
                .setDescription("iPhone is a revolutionary new mobile phone that allows you to make a call by simply tapping a name or number in your address book, a favorites list, or a call log. It also automatically syncs all your contacts from a PC, Mac, or Internet service. And it lets you select and listen to voicemail messages in whatever order you want just like email.")
                .setPrices(iPhonePrices)
                .build();              
    }
    
    public static IProduct appleCinema() {
        Map<Enum<?>, Double> appleCinemaPrices = new HashMap<>();
        appleCinemaPrices.put(Currencies.EURO, 86.31);
        appleCinemaPrices.put(Currencies.POUND_STERLING, 67.38);
        appleCinemaPrices.put(Currencies.US_DOLLAR, 110.00);
        return Product.get()
                .setSearchKey("applecinema")
                .setName("Apple Cinema 30\"")
                .setDescription("The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed specifically for the creative professional, this display provides more space for easier access to all the tools and palettes needed to edit, format and composite your work. Combine this display with a Mac Pro, MacBook Pro, or PowerMac G5 and there's no limit to what you can achieve. ")
                .setPrices(appleCinemaPrices)
                .build();              
    }
    
    public static IProduct canonEOS5D() {
        Map<Enum<?>, Double> canonEOS5DPrices = new HashMap<>();
        canonEOS5DPrices.put(Currencies.EURO, 76.89);
        canonEOS5DPrices.put(Currencies.POUND_STERLING, 60.03);
        canonEOS5DPrices.put(Currencies.US_DOLLAR, 98.00);
        return Product.get()
                .setSearchKey("canoneos")
                .setName("Canon EOS 5D")
                .setDescription("Canon's press material for the EOS 5D states that it 'defines (a) new D-SLR category', while we're not typically too concerned with marketing talk this particular statement is clearly pretty accurate. The EOS 5D is unlike any previous digital SLR in that it combines a full-frame (35 mm sized) high resolution sensor (12.8 megapixels) with a relatively compact body (slightly larger than the EOS 20D, although in your hand it feels noticeably 'chunkier').")
                .setPrices(canonEOS5DPrices)
                .build();              
    }

}
