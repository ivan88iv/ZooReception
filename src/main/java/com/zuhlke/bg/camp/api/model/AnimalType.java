package com.zuhlke.bg.camp.api.model;

public enum AnimalType {
    LION(true, false),
    TIGER(true, false),
    CROCODILE(true, false),
    SNAKE(true, false),
    ZEBRA(false, true),
    HORSE(false, true),
    DONKEY(false, true),
    HEN(false, true),
    SHEEP(false, true),
    COW(false, true),
    MONKEY(true, true),
    ELEPHANT(false, true),
    PIG(true, true),
    DOG(true, true),
    BEAR(true, true);

    private boolean eatsMeat;

    private boolean eatsHerbs;

    AnimalType(boolean eatsMeat, boolean eatsHerbs) {
        this.eatsMeat = eatsMeat;
        this.eatsHerbs = eatsHerbs;
    }

    public boolean isCarnivorous() {
        return eatsMeat && !eatsHerbs;
    }

    public boolean isHerbivorous() {
        return !eatsMeat && eatsHerbs;
    }

    public boolean isOmnivorous() {
        return eatsHerbs && eatsMeat;
    }
}
