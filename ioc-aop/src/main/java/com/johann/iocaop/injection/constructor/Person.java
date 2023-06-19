package com.johann.iocaop.injection.constructor;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description: Person
 * @Auther: Johann
 * @Version: 1.0
 */
public class Person {

    private String name;

    private Integer age;

    private List hobbies;

    private Address address;

    public Person() {
    }

    public Person(String name, Integer age, List hobbies, Address address) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + hobbies +
                ", address=" + address +
                '}';
    }
}
