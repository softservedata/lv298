package com.softserve.edu.opencart.data.countrystate;

public enum Countryes {
    UKRAINE(220), USA(223), POLAND(170);

    private int option;

    private Countryes(int option) {
	this.option = option;
    }

    public int getOption() {
	return option;
    }
}
