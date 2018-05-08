package com.softserve.edu.opencart.data;

public enum GeoZones {
    UK_VAT_ZONE("UK VAT Zone"),
    UK_SHIPPING("UK Shipping");

    // *********Fields*********
    public String geoZoneName;

    // *********Constructor*********
    GeoZones(final String name) {
        this.geoZoneName = name;
    }

    // *********Methods*********
    @Override
    public String toString() {
        return geoZoneName;
    }

    public String getName() {
        return geoZoneName;
    }

}