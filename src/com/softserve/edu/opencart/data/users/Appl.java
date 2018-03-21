package com.softserve.edu.opencart.data.users;

public class Appl {

    public static void main(String[] args) {
        // 1 Too many parameters
//        User user = new User("firstname", "lastname",
//                "eMail", "telephone", "fax", "company",
//                "addressMain", "addressAdditional",
//                "city", "postCode", "country", "state", "password", true);
//        System.out.println("login: " + user.getEmail());
        //
        // 2 Use Defaul constructor and setters
//        User user = new User();
//        user.setFirstname("firstname");
//        user.setLastname("lastname");
//        user.setEmail("eMail");
//        user.setTelephone("telephone");
//        user.setFax("fax");
//        user.setCompany("company");
//        user.setAddressMain("addressMain");
//        user.setAddressAdditional("addressAdditional");
//        user.setCity("city");
//        user.setPostCode("postCode");
//        user.setCountry("country");
//        user.setState("state");
//        user.setPassword("password");
//        user.setSubscribe(true);
//        System.out.println("login: " + user.getEmail());
        //
        // 3 Fluent interface
//        User user = new User()
//            .setFirstname("firstname")
//            .setLastname("lastname")
//            //.setEmail("eMail")
//            .setTelephone("telephone")
//            .setFax("fax")
//            .setCompany("company")
//            .setAddressMain("addressMain")
//            .setAddressAdditional("addressAdditional")
//            .setCity("city")
//            .setPostCode("postCode")
//            .setCountry("country")
//            .setState("state")
//            .setPassword("password")
//            .setSubscribe(true);
//        System.out.println("login: " + user.getEmail().length());
        //
        // 4 Add Static Factory
//        User user = User.get()
//                .setFirstname("firstname")
//                .setLastname("lastname")
//                //.setEmail("eMail")
//                .setTelephone("telephone")
//                .setFax("fax")
//                .setCompany("company")
//                .setAddressMain("addressMain")
//                .setAddressAdditional("addressAdditional")
//                .setCity("city")
//                .setPostCode("postCode")
//                .setCountry("country")
//                .setState("state")
//                .setPassword("password")
//                .setSubscribe(true);
//        System.out.println("login: " + user.getEmail().length());
        //
        // 5 Add Builder
//        User user = User.get()
//                .setFirstname("firstname")
//                .setLastname("lastname")
//                .setEmail("email")
//                .setTelephone("telephone")
//                .setAddressMain("addressMain")
//                .setCity("city")
//                .setPostCode("postCode")
//                .setCountry("country")
//                .setState("state")
//                .setPassword("password")
//                .setSubscribe(true)
//                .setFax("fax")
//                .build();
//        System.out.println("login: " + user.setEmail("111")); // Error
//        System.out.println("login: " + user.getEmail());
        //
        // 6 Add Dependency Inversion
//        IUser user = User.get()
//                .setFirstname("firstname")
//                .setLastname("lastname")
//                .setEmail("email")
//                .setTelephone("telephone")
//                .setAddressMain("addressMain")
//                .setCity("city")
//                .setPostCode("postCode")
//                .setCountry("country")
//                .setState("state")
//                .setPassword("password")
//                .setSubscribe(true)
//                .setFax("fax")
//                .build();
        //System.out.println("login: " + ((User)user).setEmail("111"));
//        System.out.println("login: " + user.getEmail());
        //
        // 7, 8 Repository by using singletone
        IUser user = UserRepository.get()
                .customer();
        System.out.println("login: " + user.getEmail());
    }
}
