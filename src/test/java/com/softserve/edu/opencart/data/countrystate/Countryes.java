package com.softserve.edu.opencart.data.countrystate;

public enum Countryes {
    UKRAINE("220"), USA("223"), POLAND("170");

    private String value;

    private Countryes(String value) {
	this.value = value;
    }
    @Override
    public String toString() {
	return value;
    }
}
