package com.zuhlke.bg.camp.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimalFood {
    private final String name;

    private final FoodType type;

    private final double pricePerKg;

    @JsonCreator
    AnimalFood(@JsonProperty("name") String name,
               @JsonProperty("type") FoodType type,
               @JsonProperty("pricePerKg") double price) {

        this.name = name;
        this.type = type;
        this.pricePerKg = price;
    }

    public String getName() {
        return name;
    }

    public FoodType getType() {
        return type;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }
}
