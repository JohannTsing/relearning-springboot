package com.johann.iocaop.injection.constructor;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: Address
 * @Auther: Johann
 * @Version: 1.0
 */
public class Address {

    private String city;

    private String street;

    public Address() {
    }

    public Address(String city, String street) {
        this.city = city;
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
