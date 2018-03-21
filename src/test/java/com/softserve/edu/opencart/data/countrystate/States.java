package com.softserve.edu.opencart.data.countrystate;

public enum States {
    Dolnoslaskie("2631"), Lodzkie("2633"), Lubelskie("2634"),	//Poland
    Kiyv("3490"), Lviv("3491"), Ternopil("3500"),		//Ukraine
    Alaska("3614"), California("3624"); 			//USA

    private String value;

    private States(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return value;
    }
    
}
