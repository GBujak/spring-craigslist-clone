package com.gbujak.springcraigslistclone.model;

public enum AdvertisementCategory {
    COMMUNITY("spolecznosc", "społeczność"),
    FOR_SALE("na-sprzedaz", "na sprzedaż"),
    JOBS("praca", "praca"),
    HOUSING("mieszkanie", "mieszkanie"),
    SERVICES("uslugi", "usługi");

    private final String name;
    private final String humanReadableName;

    AdvertisementCategory(String name, String humanReadableName) {
        this.name = name;
        this.humanReadableName = humanReadableName;
    }

    public String getName() {
        return name;
    }

    public String getHumanReadableName() {
        return humanReadableName;
    }
}
