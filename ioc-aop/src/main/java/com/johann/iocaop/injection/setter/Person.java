package com.johann.iocaop.injection.setter;

import java.util.List;
import java.util.Map;

/**
 * @Description: Person
 * @Auther: Johann
 * @Version: 1.0
 */
public class Person {
    private String name;

    private Integer age;

    private List hobbies;

    private Map scores;

    private Address address;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setHobbies(List hobbies) {
        this.hobbies = hobbies;
    }

    public void setScores(Map scores) {
        this.scores = scores;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + hobbies +
                ", scores=" + scores +
                ", address=" + address +
                '}';
    }
}
