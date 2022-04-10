package com.zuhlke.bg.camp.service.model;

import com.zuhlke.bg.camp.api.exception.InvalidCriteriaException;

import java.util.Arrays;

public enum WalkCriteria {
    CARNIVOROUS("carnivorous"),
    HERBIVOROUS("herbivorous"),
    OMNIVOROUS("omnivorous");

    private final String name;

    WalkCriteria(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static WalkCriteria fromValue(String value) {
        if(value == null || value.isEmpty()) {
            return null;
        }

        return Arrays.stream(WalkCriteria.values())
                .filter((criteria) -> criteria.name.equals(value))
                .findFirst()
                .orElseThrow(() -> new InvalidCriteriaException(value));
    }
}
