package com.softserve.edu.opencart.data.users;

public interface IUser {

    String getFirstname();

    String getLastname();

    String getEmail();

    String getTelephone();

    String getFax();

    String getCompany();

    String getAddressMain();

    String getAddressAdditional();

    String getCity();

    String getPostCode();

    String getCountry();

    String getState();

    String getPassword();

    boolean isSubscribe();

}
