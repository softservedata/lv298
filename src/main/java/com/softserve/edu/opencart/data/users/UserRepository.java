package com.softserve.edu.opencart.data.users;

public final class UserRepository {

    private static volatile UserRepository instance = null;

    // *********Constructor*********
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

    // *********Repository*********
    public IUser defaultUser() {
        return customer();
    }

    public IUser customer() {
        return User.get()
                .setFirstname("firstname")
                .setLastname("lastname")
                .setEmail("iwish@gmail.com")
                .setTelephone("telephone")
                .setAddressMain("addressMain")
                .setCity("city")
                .setPostCode("postCode")
                .setCountry("country")
                .setState("state")
                .setPassword("qwerty")
                .setSubscribe(true)
                .setFax("fax")
                .build();
    }

    public IUser customer1() {
        return User.get()
                .setFirstname("firstname")
                .setLastname("lastname")
                .setEmail("customer1@gmail.com")
                .setTelephone("telephone")
                .setAddressMain("addressMain")
                .setCity("city")
                .setPostCode("postCode")
                .setCountry("country")
                .setState("state")
                .setPassword("qwerty")
                .setSubscribe(true)
                .setFax("fax")
                .build();
    }

    //public IUser admin() {}
    //public List<IUser> fromExcel() {}
    //public List<IUser> fromDB() { create class, read, max 5-7 lines}

}
