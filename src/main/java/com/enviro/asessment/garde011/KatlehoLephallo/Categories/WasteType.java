package com.enviro.asessment.garde011.KatlehoLephallo.Categories;

public enum WasteType {

    RECYCLABLE("Recyclable"),
    COMPOSTABLE("Compostable"),
    NON_RECYCLABLE("Non-Recyclable");

    private final String description;

    WasteType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
