package com.enviro.asessment.garde011.KatlehoLephallo.Categories;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WasteCategoryService {

    private final WasteCategoryRepository wasteCategoryRepository;

    public WasteCategoryService(WasteCategoryRepository wasteCategoryRepository) {
        this.wasteCategoryRepository = wasteCategoryRepository;
    }

    public List<WasteCategory> getAllWasteCategories() {
        return wasteCategoryRepository.findAll();
    }
}

