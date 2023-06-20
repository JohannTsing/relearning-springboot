package com.johann.iocaop.injection.autowire;

/**
 * @Description: AddressAutowire
 * @Auther: Johann
 * @Version: 1.0
 */
public class AddressAutowire {
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
        return "AddressAutowire{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
