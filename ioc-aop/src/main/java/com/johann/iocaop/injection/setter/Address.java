package com.johann.iocaop.injection.setter;

/**
 * @Description: Address
 * @Auther: Johann
 * @Version: 1.0
 */
public class Address {
    private String city;

    private String street;

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
