package com.enviro.asessment.garde011.KatlehoLephallo.Categories;


public class WasteCategoryNotFoundException extends RuntimeException {

    public WasteCategoryNotFoundException(Long id) {
        super("Could not find waste category " + id);
    }
}
