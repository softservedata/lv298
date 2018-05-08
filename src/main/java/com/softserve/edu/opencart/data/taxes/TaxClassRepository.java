package com.softserve.edu.opencart.data.taxes;

import com.softserve.edu.opencart.data.GeoZones;
import com.softserve.edu.opencart.tools.DBReader;

import java.util.List;

public final class TaxClassRepository {

    private static volatile TaxClassRepository instance = null;

    // *********Constructor*********
    private TaxClassRepository() {
    }

    public static TaxClassRepository get() {
        if (instance == null) {
            synchronized (TaxClassRepository.class) {
                if (instance == null) {
                    instance = new TaxClassRepository();
                }
            }
        }
        return instance;
    }

    public TaxClass ByName(String name){
        TaxClass result = null;
        for (TaxClass currentTaxClass : TaxClassRepository.fromDbTaxClass()){
            if (currentTaxClass.getTitle().equalsIgnoreCase(name)){
                result = currentTaxClass;
            }
        }
        return result;
    }

    public TaxClass taxebleGoods () {
        TaxClass taxebleGoods = new TaxClass("Taxable Goods", "Taxed goods");
        taxebleGoods.addParameter(TaxRateRepository.get().vat(),TaxClass.BasedOn.SHIPPING_ADDRESS,1);
        taxebleGoods.addParameter(TaxRateRepository.get().ecoTax(),TaxClass.BasedOn.SHIPPING_ADDRESS,2);
        return  taxebleGoods;
    }

    public TaxClass downloadableProducts () {
        TaxClass taxebleGoods = new TaxClass("Downloadable Products", "Downloadable");
        taxebleGoods.addParameter(TaxRateRepository.get().vat(),TaxClass.BasedOn.PAYMENT_ADDRESS,1);
        taxebleGoods.addParameter(TaxRateRepository.get().ecoTax(),TaxClass.BasedOn.STORE_ADDRESS,0);
        return  taxebleGoods;
    }

    public TaxClass noTax () {
        TaxClass taxebleGoods = new TaxClass("---None---", "no Tax");
        return  taxebleGoods;
    }

    // *********Repository*********
    public TaxClass defaultTaxClass() {
        return noTax();
    }

    public static List<TaxClass> fromDbTaxClass(){
        return fromDbTaxClass("SELECT tax_class.title, tax_class.description," +
                " tax_rate.name AS taxName, tax_rule.based, tax_rule.priority" +
                "  \n" +
                "FROM ((tax_rule \n" +
                "JOIN tax_class ON tax_class.tax_class_id = tax_rule" +
                ".tax_class_id)\n" +
                "JOIN tax_rate ON tax_rate.tax_rate_id = tax_rule.tax_rate_id);");
    }

    public static List<TaxClass> fromDbTaxClass(String sqlSelect) {
        return TaxClass.getByList(new DBReader(sqlSelect).getAllCells());

    }

}
