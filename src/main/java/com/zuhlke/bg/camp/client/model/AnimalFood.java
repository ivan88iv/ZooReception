package com.zuhlke.bg.camp.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AnimalFood {
    private final String name;

    private final FoodType type;

    private final double pricePerKg;

    @JsonCreator
    public AnimalFood(@JsonProperty("name") String name,
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnimalFood that = (AnimalFood) o;
        return Double.compare(that.pricePerKg, pricePerKg) == 0 &&
                Objects.equals(name, that.name) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, pricePerKg);
    }
}
