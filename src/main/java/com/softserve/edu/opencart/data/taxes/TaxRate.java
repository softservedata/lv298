package com.softserve.edu.opencart.data.taxes;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.GeoZones;
import com.softserve.edu.opencart.data.products.IProduct;
import com.softserve.edu.opencart.data.products.Product;
import com.softserve.edu.opencart.tools.RegexUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface IName {
    IRate setName(String name);
}

interface IRate {
    IType setRate(double rate);
    IType setRate(String rate);
}

interface IType {
    IGeoZone setType(TaxRate.Type type);
    IGeoZone setType(String type);
}

interface IGeoZone {
    ITaxRateBuild setGeoZone(GeoZones geoZone);
    ITaxRateBuild setGeoZone(String geoZone);
}

interface ITaxRateBuild {
    ITaxRate buildTaxRate();
}

public class TaxRate implements IName,IRate, IType,IGeoZone,ITaxRateBuild,ITaxRate{

    private static final String NAME_HEADER = "name";
    private static final String GEO_ZONE_HEADER = "geoZone";
    private static final String TYPE_HEADER = "type";
    private static final String RATE_HEADER = "rate";

    public enum Type {
        FIXED,
        PERCENTAGE
    }

    private String name;
    private double rate;
    private Type type;
    private GeoZones geoZone;


    @Override
    public String toString() {
        return  String.format("%s; %s; %s; %s", getName(), getRate(), getType(), getGeoZone());
    }

    public static List<ITaxRate> getByList(List<List<String>> rows) {
        List<ITaxRate> result = new ArrayList<>();
        Map<String, Integer> headers = new HashMap<>();
        for (List<String> currentRow : rows) {
            if (!RegexUtils.isWords(currentRow.toString())) {

                //build Tax
                result.add(
                        TaxRate.get()
                            .setName(currentRow.get(headers.get(NAME_HEADER)))
                            .setRate(currentRow.get(headers.get(RATE_HEADER)))
                            .setType(currentRow.get(headers.get(TYPE_HEADER)))
                            .setGeoZone(currentRow.get(headers.get(GEO_ZONE_HEADER)))
                            .buildTaxRate()
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

    // *********Setters*********
    public static IName get() {
        return new TaxRate();
    }

    public IRate setName(String name) {
        this.name = name;
        return this;
    }


    public IType setRate(String rate) {
        this.rate = Double.parseDouble(rate);
        return this;
    }

    public IType setRate(double rate) {
        this.rate = rate;
        return this;
    }

    public IGeoZone setType(Type type) {
        this.type = type;
        return this;
    }

    public IGeoZone setType(String type) {
        if (type.equalsIgnoreCase("P")) {
            this.type = Type.PERCENTAGE;
        } else  if (type.equalsIgnoreCase("F")) {
            this.type = Type.FIXED;
        }
        return this;
    }

    public ITaxRateBuild setGeoZone(GeoZones geoZone) {
        this.geoZone = geoZone;
        return this;
    }

    public ITaxRateBuild setGeoZone(String geoZone) {
        for (GeoZones zone : GeoZones.values()){
            if (zone.geoZoneName.equalsIgnoreCase(geoZone)){
                this.geoZone = zone;
            }
        }
        return this;
    }

    public ITaxRate buildTaxRate() {
        return this;
    }

    // *********Getters*********
    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public Type getType() {
        return type;
    }

    public GeoZones getGeoZone() {
        return geoZone;
    }

}
