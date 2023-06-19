package com.johann.iocaop.injection.autowire;

/**
 * @Description: Person
 * @Auther: Johann
 * @Version: 1.0
 */
public class Person {

    private String name;

    private int age;

    private Address address;

    private Pet pet;

    public Person() {
    }
    public Person(String name, int age, Address address, Pet pet) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.pet = pet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", pet=" + (pet==null?"null":pet.say()) +
                '}';
    }
}
