package com.enviro.asessment.garde011.KatlehoLephallo.Guidelines;

import com.enviro.asessment.garde011.KatlehoLephallo.Categories.WasteCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DisposalGuideline {

    private @Id @GeneratedValue Long id;
    private String guideline;

    @ManyToOne
    private WasteCategory wasteCategory;

    // Default constructor
    public DisposalGuideline() {}

    // Constructor
    public DisposalGuideline(String guideline, WasteCategory wasteCategory) {
        this.guideline = guideline;
        this.wasteCategory = wasteCategory;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getGuideline() {
        return guideline;
    }

    public WasteCategory getWasteCategory() {
        return wasteCategory;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }

    public void setWasteCategory(WasteCategory wasteCategory) {
        this.wasteCategory = wasteCategory;
    }
}

