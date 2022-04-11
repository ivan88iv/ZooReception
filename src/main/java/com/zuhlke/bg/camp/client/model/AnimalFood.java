package com.zuhlke.bg.camp.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AnimalFood {
    private final String name;

    private final FoodType type;

    private final double price;

    private final PriceUnit priceUnit;

    @JsonCreator
    public AnimalFood(@JsonProperty("name") String name,
                      @JsonProperty("type") FoodType type,
                      @JsonProperty("price") double price,
                      @JsonProperty("priceUnit") PriceUnit priceUnit) {

        this.name = name;
        this.type = type;
        this.price = price;
        this.priceUnit = priceUnit;
    }

    public String getName() {
        return name;
    }

    public FoodType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public PriceUnit getPriceUnit() {
        return priceUnit;
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
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(name, that.name) &&
                type == that.type &&
                priceUnit == that.priceUnit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, price, priceUnit);
    }
}
