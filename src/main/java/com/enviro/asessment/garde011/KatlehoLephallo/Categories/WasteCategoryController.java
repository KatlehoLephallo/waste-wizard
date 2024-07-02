package com.enviro.asessment.garde011.KatlehoLephallo.Categories;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/waste-wizard")
public class WasteCategoryController {

    private final WasteCategoryService wasteCategoryService;
    private final WasteCategoryRepository repository;
    private final WasteCategoryModelAssembler assembler;


    public WasteCategoryController(WasteCategoryService wasteCategoryService, WasteCategoryRepository repository, WasteCategoryModelAssembler assembler) {
        this.wasteCategoryService = wasteCategoryService;
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/waste-categories")
    public CollectionModel<EntityModel<WasteCategory>> getAllWasteCategories() {
        //showing all categories

        List<EntityModel<WasteCategory>> wasteCategories = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(wasteCategories, linkTo(methodOn(WasteCategoryController.class).getAllWasteCategories()).withSelfRel());
    }

    @GetMapping("/waste-categories/{id}")
    public EntityModel<WasteCategory> getWasteCategory(@PathVariable Long id) {
        // get category by id

        WasteCategory wasteCategory = repository.findById(id)
                .orElseThrow(() -> new WasteCategoryNotFoundException(id));

        return assembler.toModel(wasteCategory);
    }

    @PostMapping("/waste-categories")
    public ResponseEntity<?> createWasteCategory(@RequestBody WasteCategory newWasteCategory) {
        // creating new category

        EntityModel<WasteCategory> entityModel = assembler.toModel(repository.save(newWasteCategory));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/waste-categories/{id}")
    public WasteCategory updateWasteCategory(@RequestBody WasteCategory newWasteCategory, @PathVariable Long id) {
        // updating an existing category

        return repository.findById(id)
                .map(wasteCategory -> {
                    wasteCategory.setName(newWasteCategory.getName());
                    wasteCategory.setType(newWasteCategory.getType());
                    return repository.save(wasteCategory);
                })
                .orElseThrow(() -> new WasteCategoryNotFoundException(id));
    }

    @DeleteMapping("/waste-categories/{id}")
    public ResponseEntity<?> deleteWasteCategory(@PathVariable Long id) {
        WasteCategory wasteCategory = repository.findById(id)
                .orElseThrow(() -> new WasteCategoryNotFoundException(id));
        repository.delete(wasteCategory);
        return ResponseEntity.noContent().build();
    }

}
