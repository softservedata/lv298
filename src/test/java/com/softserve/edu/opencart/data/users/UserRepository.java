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

    public IUser failedRegistrationUser() {
        return User.get().setFirstname("firstname")
                .setLastname("lastname").setEmail("hahaha@gmail.com")
                .setTelephone("telephone")
                .setAddressMain("addressMain")
                .setCity("city")
                .setPostCode("postCode")
                .setCountry(Countryes.POLAND.toString())
                .setState(States.LUBELSKIE.toString())
                .setPassword("qwerty")
                .setSubscribe(true)
                .setFax("fax")
                .build();
    }

    public IUser successRegistrationUser() {
        return User.get()
                .setFirstname("Nazar")
                .setLastname("Yermolenko")
                .setEmail("nazaron1995@gmail.com")
                .setTelephone("+380976437780")
                .setAddressMain("Lukasha str. 4")
                .setCity("Lviv")
                .setPostCode("79032")
                .setCountry(Countryes.UKRAINE.toString())
                .setState(States.LVIV.toString())
                .setPassword("qwerty12345")
                .setSubscribe(false)
                .build();
    }


    public IUser customer(){
        return User.get()
                .setFirstname("Customer")
                .setLastname("Simply")
                .setEmail("qwerty@gmail.com")
                .setTelephone("+380976437780")
                .setAddressMain("Somewhere str.")
                .setCity("SomeWhere")
                .setPostCode("70563")
                .setCountry(Countryes.USA.toString())
                .setState(States.CALIFORNIA.toString())
                .setPassword("QWERTY123456")
                .setSubscribe(false)
                .build();
    }
}
