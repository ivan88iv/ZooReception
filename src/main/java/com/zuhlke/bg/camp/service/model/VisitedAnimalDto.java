package com.zuhlke.bg.camp.service.model;

import com.zuhlke.bg.camp.client.model.Gender;

public class VisitedAnimalDto {
    private final String name;

    private final AnimalCategory category;

    private final String species;

    private final int age;

    private final Gender gender;

    private final int cageNumber;

    private VisitedAnimalDto(String name,
                            String species,
                            AnimalCategory category,
                            int age,
                            Gender gender,
                            int cageNumber) {
        this.name = name;
        this.species = species;
        this.category = category;
        this.age = age;
        this.gender = gender;
        this.cageNumber = cageNumber;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public AnimalCategory getCategory() {
        return category;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public int getCageNumber() {
        return cageNumber;
    }

    public static class Builder {
        private String name;

        private AnimalCategory category;

        private String species;

        private int age;

        private Gender gender;

        private int cageNumber;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCategory(AnimalCategory category) {
            this.category = category;
            return this;
        }

        public Builder withSpecies(String species) {
            this.species = species;
            return this;
        }

        public Builder withAge(int age) {
            this.age = age;
            return this;
        }

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder withCageNumber(int cageNumber) {
            this.cageNumber = cageNumber;
            return this;
        }

        public VisitedAnimalDto build() {
            return new VisitedAnimalDto(name, species, category, age, gender, cageNumber);
        }
    }
}