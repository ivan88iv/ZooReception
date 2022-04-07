package com.zuhlke.bg.camp.api.model;

public class Animal {
    private final String name;

    private final AnimalType type;

    private final int age;

    private final Gender gender;


    // HIX age -> string
    public Animal(String name, AnimalType type, int age, Gender gender) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public AnimalType getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
}