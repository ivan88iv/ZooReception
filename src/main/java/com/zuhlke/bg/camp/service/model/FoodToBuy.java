package com.zuhlke.bg.camp.service.model;

import com.zuhlke.bg.camp.client.model.FoodType;

public class FoodToBuy {
    private final String name;

    private final FoodType type;

    private final double quantity;

    private final String unit;

    public FoodToBuy(String name, FoodType type, double quantity, String unit) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public FoodType getType() {
        return type;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }
}
