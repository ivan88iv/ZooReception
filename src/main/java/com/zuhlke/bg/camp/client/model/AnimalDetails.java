package com.zuhlke.bg.camp.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class AnimalDetails {
    private final String name;

    private final String animalType;

    private final Set<AnimalFood> acceptableFood;

    @JsonCreator
    AnimalDetails(@JsonProperty("name") String name,
                  @JsonProperty("animalType") String type,
                  @JsonProperty("acceptableFood") Set<AnimalFood> food) {

        this.name = name;
        this.animalType = type;
        this.acceptableFood = food;
    }

    public String getName() {
        return name;
    }

    public String getAnimalType() {
        return animalType;
    }

    public Set<AnimalFood> getAcceptableFood() {
        return acceptableFood;
    }
}
