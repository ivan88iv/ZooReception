package com.zuhlke.bg.camp.client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = ManagerAnimalDto.Builder.class)
public class ManagerAnimalDto {
    private final String name;

    private final String type;

    private final int age;

    private final Gender gender;

    private final FoodType[] foodTypes;

    private final int cageNumber;

    private ManagerAnimalDto(String name,
                     String type,
                     int age,
                     Gender gender,
                     FoodType[] foodType,
                     int cageNumber) {

        this.name = name;
        this.type = type;
        this.age = age;
        this.gender = gender;
        this.foodTypes = foodType;
        this.cageNumber = cageNumber;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public FoodType[] getFoodTypes() {
        return foodTypes;
    }

    public int getCageNumber() {
        return cageNumber;
    }

    @JsonPOJOBuilder
    static class Builder {
        private String name;

        private String type;

        private int age;

        private Gender gender;

        private FoodType[] foodTypes;

        private int cageNumber;

        Builder withName(final String name) {
            this.name = name;
            return this;
        }

        Builder withType(final String type) {
            this.type = type;
            return this;
        }

        Builder withAge(final int age) {
            this.age = age;
            return this;
        }

        Builder withGender(final Gender gender) {
            this.gender = gender;
            return this;
        }

        Builder withFoodTypes(final FoodType[] foodType) {
            this.foodTypes = foodType;
            return this;
        }

        Builder withCageNumber(final int cageNumber) {
            this.cageNumber = cageNumber;
            return this;
        }

        ManagerAnimalDto build() {
            return new ManagerAnimalDto(name, type, age, gender, foodTypes, cageNumber);
        }
    }
}
