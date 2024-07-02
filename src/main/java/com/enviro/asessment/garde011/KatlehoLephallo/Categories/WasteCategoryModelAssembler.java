package com.enviro.asessment.garde011.KatlehoLephallo.Categories;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WasteCategoryModelAssembler implements RepresentationModelAssembler<WasteCategory, EntityModel<WasteCategory>> {

    @Override
    public EntityModel<WasteCategory> toModel(WasteCategory wasteCategory) {
        return EntityModel.of(wasteCategory,
                linkTo(methodOn(WasteCategoryController.class).getWasteCategory(wasteCategory.getId())).withSelfRel(),
                linkTo(methodOn(WasteCategoryController.class).getAllWasteCategories()).withRel("wasteCategories"));
    }

//    @Override
//    public CollectionModel<EntityModel<WasteCategory>> toCollectionModel(Iterable<? extends WasteCategory> entities) {
//        return RepresentationModelAssembler.super.toCollectionModel(entities);
//    }
}

