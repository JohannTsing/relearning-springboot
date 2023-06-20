package com.johann.iocaop.injection.autowire;

/**
 * @Description: Person
 * @Auther: Johann
 * @Version: 1.0
 */
public class Person {

    private String name;

    private int age;

    private AddressAutowire addressAutowire;

    private Pet pet;

    public Person() {
    }
    public Person(String name, int age, AddressAutowire addressAutowire, Pet pet) {
        this.name = name;
        this.age = age;
        this.addressAutowire = addressAutowire;
        this.pet = pet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddressAutowire(AddressAutowire addressAutowire) {
        this.addressAutowire = addressAutowire;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addressAutowire=" + addressAutowire +
                ", pet=" + (pet==null?"null":pet.say()) +
                '}';
    }
}
