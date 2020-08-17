package com.gbujak.springcraigslistclone.model;

public enum AdvertisementCategory {
    COMMUNITY("społeczność"),
    FOR_SALE("na sprzedaż"),
    JOBS("praca"),
    HOUSING("mieszkanie"),
    SERVICES("usługi");

    private final String name;

    AdvertisementCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
