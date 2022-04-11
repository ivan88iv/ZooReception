package com.zuhlke.bg.camp.service.model;

import java.util.Set;

public class AnimalDonationDto {
    private final String message;

    private final Set<FoodToBuy> weCanBuy;

    public AnimalDonationDto(String message, Set<FoodToBuy> weCanBuy) {
        this.message = message;
        this.weCanBuy = weCanBuy;
    }

    public String getMessage() {
        return message;
    }

    public Set<FoodToBuy> getWeCanBuy() {
        return weCanBuy;
    }
}
