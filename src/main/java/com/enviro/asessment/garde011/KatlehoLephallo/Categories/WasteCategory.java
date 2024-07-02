package com.enviro.asessment.garde011.KatlehoLephallo.Categories;

import com.enviro.asessment.garde011.KatlehoLephallo.Guidelines.DisposalGuideline;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class WasteCategory {

    private @Id @GeneratedValue Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Enumerated(EnumType.STRING)
    private WasteType type;

//    private String code;

    @OneToMany(mappedBy = "wasteCategory")
    private List<DisposalGuideline> disposalGuidelines;

    public WasteCategory() {}

    public WasteCategory(String name, WasteType type){
        this.name = name;
        this.type = type;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WasteType getType() {
        return type;
    }

//    public String getCode() {
//        return code;
//    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(WasteType type) {
        this.type = type;
    }

//    public void setCode(String code) {
//        this.code = code;
//    }
}

