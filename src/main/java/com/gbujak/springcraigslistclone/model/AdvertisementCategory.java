package com.gbujak.springcraigslistclone.model;

public enum AdvertisementCategory {
    COMMUNITY("społeczność", "spolecznosc"),
    FOR_SALE("na sprzedaż", "na-sprzedaz"),
    JOBS("praca", "praca"),
    HOUSING("mieszkanie", "mieszkanie"),
    SERVICES("usługi", "uslugi");

    private final String name;
    private final String slug;

    AdvertisementCategory(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }
}
