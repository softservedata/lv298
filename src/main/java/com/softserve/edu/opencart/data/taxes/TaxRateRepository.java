package com.softserve.edu.opencart.data.taxes;

import com.softserve.edu.opencart.data.GeoZones;
import com.softserve.edu.opencart.data.applications.ApplicationSourceRepository;
import com.softserve.edu.opencart.pages.Application;
import com.softserve.edu.opencart.tools.DBReader;

import java.util.ArrayList;
import java.util.List;

public final class TaxRateRepository {

    private static volatile TaxRateRepository instance = null;

    // *********Constructor*********
    private TaxRateRepository() {
    }

    public static TaxRateRepository get() {
        if (instance == null) {
            synchronized (TaxRateRepository.class) {
                if (instance == null) {
                    instance = new TaxRateRepository();
                }
            }
        }
        return instance;
    }

    public ITaxRate byName(String taxRate){
        ITaxRate result = null;
        for (ITaxRate currentTax : TaxRateRepository.fromDbTaxRates()){
            if (currentTax.getName().equalsIgnoreCase(taxRate)){
                result = currentTax;
            }
        }
        return result;
    }

    public ITaxRate ecoTax () {
        return TaxRate.get()
                .setName("Eco Tax (-2.00)")
                .setRate(2.00)
                .setType(TaxRate.Type.FIXED)
                .setGeoZone(GeoZones.UK_VAT_ZONE)
                .buildTaxRate();
    }

    public ITaxRate vat () {
        return TaxRate.get()
                .setName("VAT (20%)")
                .setRate(20.00)
                .setType(TaxRate.Type.PERCENTAGE)
                .setGeoZone(GeoZones.UK_VAT_ZONE)
                .buildTaxRate();
    }

    public static List<ITaxRate> fromDbTaxRates(){
        return fromDbTaxRates("SELECT  tax_rate.name, tax_rate.rate, tax_rate" +
                ".type,  geo_zone.name AS geoZone\n" +
                "FROM tax_rate \n" +
                "JOIN geo_zone ON geo_zone.geo_zone_id = tax_rate.geo_zone_id;");
    }

    public static List<ITaxRate> fromDbTaxRates(String sqlSelect) {
        return TaxRate.getByList(new DBReader(sqlSelect).getAllCells());
    }
    // *********Repository*********
    public ITaxRate defaultTaxeRate() {
        return ecoTax();
    }

}
