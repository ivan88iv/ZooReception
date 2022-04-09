package com.zuhlke.bg.camp.service.model;

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

    public static WalkCriteria fromValue(final String value) {
        if(value == null) {
            return null;
        }

        return Arrays.stream(WalkCriteria.values())
                .filter((criteria) -> criteria.name.equals(value))
                .findFirst()
                .orElseThrow(() -> new InvalidCriteriaException(value));
    }
}
