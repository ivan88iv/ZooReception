package com.zuhlke.bg.camp.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Amount {

    private final double money;

    @JsonCreator
    Amount(@JsonProperty("money") double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }
}
