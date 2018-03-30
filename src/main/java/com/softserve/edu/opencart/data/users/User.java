package com.softserve.edu.opencart.data.users;

//*********Builder Pattern*********
interface IFirstname {
    ILastname setFirstname(String firstname);
}

interface ILastname {
    IEmail setLastname(String lastname);
}

interface IEmail {
    ITelephone setEmail(String email);
}

interface ITelephone {
    IAddressMain setTelephone(String telephone);
}

interface IAddressMain {
    ICity setAddressMain(String addressMain);
}

interface ICity {
    IPostCode setCity(String city);
}

interface IPostCode {
    ICountry setPostCode(String postCode);
}

interface ICountry {
    IState setCountry(String country);
}

interface IState {
    IPassword setState(String state);
}

interface IPassword {
    ISubscribe setPassword(String password);
}

interface ISubscribe {
    IUSerBuild setSubscribe(boolean subscribe);
}

interface IUSerBuild {
    IUSerBuild setFax(String fax);

    IUSerBuild setCompany(String company);

    IUSerBuild setAddressAdditional(String addressAdditional);

    IUser build();
}

public class User implements IFirstname, ILastname, IEmail, ITelephone, IAddressMain, ICity,
                             IPostCode, ICountry, IState, IPassword, ISubscribe, IUSerBuild,
                             IUser {
    
    //*********User Data*********
    private String firstname;
    private String lastname;
    private String email;
    private String telephone;
    private String fax;
    private String company;
    private String addressMain;
    private String addressAdditional;
    private String city;
    private String postCode;
    private String country;
    private String state;
    private String password;
    private boolean subscribe;
    
    // *********Constructor*********
    private User() {
    }
  
    public static IFirstname get() {
        return new User();
    }

    // *********Setters*********
    public ILastname setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public IEmail setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ITelephone setEmail(String email) {
        this.email = email;
        return this;
    }

    public IAddressMain setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public IUSerBuild setFax(String fax) {
        this.fax = fax;
        return this;
    }

    public IUSerBuild setCompany(String company) {
        this.company = company;
        return this;
    }

    public ICity setAddressMain(String addressMain) {
        this.addressMain = addressMain;
        return this;
    }

    public IUSerBuild setAddressAdditional(String addressAdditional) {
        this.addressAdditional = addressAdditional;
        return this;
    }

    public IPostCode setCity(String city) {
        this.city = city;
        return this;
    }

    public ICountry setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public IState setCountry(String country) {
        this.country = country;
        return this;
    }

    public IPassword setState(String state) {
        this.state = state;
        return this;
    }

    public ISubscribe setPassword(String password) {
        this.password = password;
        return this;
    }

    public IUSerBuild setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
        return this;
    }

    public IUser build() {
        return this;
    }

    // *********Getters*********
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getFax() {
        return fax;
    }

    public String getCompany() {
        return company;
    }

    public String getAddressMain() {
        return addressMain;
    }

    public String getAddressAdditional() {
        return addressAdditional;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

}
