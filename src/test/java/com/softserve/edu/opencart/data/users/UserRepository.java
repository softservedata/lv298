package com.softserve.edu.opencart.data.users;

import com.softserve.edu.opencart.data.countrystate.Countryes;
import com.softserve.edu.opencart.data.countrystate.States;

public final class UserRepository {
    
    private static volatile UserRepository instance = null;

    private UserRepository() {
    }

    public static UserRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public IUser defaultUser() {
        return customer();
    }
    
    public IUser customer() {
        return User.get()
                .setFirstname("firstname")
                .setLastname("lastname")
                .setEmail("hahaha@gmail.com")
                .setTelephone("telephone")
                .setAddressMain("addressMain")
                .setCity("city")
                .setPostCode("postCode")
                .setCountry(Countryes.UKRAINE.toString())
                .setState(States.Lviv.toString())
                .setPassword("qwerty")
                .setSubscribe(true)
                .setFax("fax")
                .build();
    }

    //public IUser admin() {}
    //public List<IUser> fromExcel() {}
    //public List<IUser> fromDB() { create class, read, max 5-7 lines}

}
