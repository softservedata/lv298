package com.softserve.edu.opencart.data.taxes;

import com.softserve.edu.opencart.data.GeoZones;

public interface ITaxRate {

    double getRate();

    String getName();

    TaxRate.Type getType();

    GeoZones getGeoZone();
}
