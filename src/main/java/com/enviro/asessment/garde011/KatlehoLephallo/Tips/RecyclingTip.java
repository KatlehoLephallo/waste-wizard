package com.enviro.asessment.garde011.KatlehoLephallo.Tips;

import com.enviro.asessment.garde011.KatlehoLephallo.Categories.WasteCategory;
import jakarta.persistence.*;

@Entity
public class RecyclingTip {

    @Id @GeneratedValue private Long id;

    private String tip;

    @ManyToOne
    private WasteCategory category;

    // Default constructor
    public RecyclingTip() {}

    // Constructor
    public RecyclingTip(String tip, WasteCategory category) {
        this.tip = tip;
        this.category = category;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTip() {
        return tip;
    }

    public WasteCategory getCategory() {
        return category;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setCategory(WasteCategory category) {
        this.category = category;
    }
}
