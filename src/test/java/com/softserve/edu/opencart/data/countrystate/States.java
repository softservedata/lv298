package com.softserve.edu.opencart.data.countrystate;

public enum States {
    DOLNOSLASKIE("2631"), LODZKIE("2633"), LUBELSKIE("2634"),	//Poland
    KIYV("3490"), LVIV("3491"), TERNOPIL("3500"),		//Ukraine
    ALASKA("3614"), CALIFORNIA("3624"); 			//USA

    private String value;

    private States(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return value;
    }
    
}
