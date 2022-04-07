package com.zuhlke.bg.camp.api.model;

import com.zuhlke.bg.camp.api.exception.InvalidCriteriaException;

import java.util.Arrays;

public enum WalkCriteria {
    NAME_ASC("nameAsc"),
    NAME_DESC("nameDesc"),
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
        return Arrays.stream(WalkCriteria.values())
                .filter((criteria) -> criteria.name.equals(value))
                .findFirst()
                .orElseThrow(() -> new InvalidCriteriaException(value));
    }
}
