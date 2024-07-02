package com.enviro.asessment.garde011.KatlehoLephallo.Guidelines;

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
public class DisposalGuidelineController {

    private final DisposalGuidelineRepository repository;
    private final DisposalGuidelineModelAssembler assembler;

    public DisposalGuidelineController(DisposalGuidelineRepository repository, DisposalGuidelineModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/disposal-guidelines")
    public CollectionModel<EntityModel<DisposalGuideline>> getAllDisposalGuidelines() {
        List<EntityModel<DisposalGuideline>> disposalGuidelines = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(disposalGuidelines, linkTo(methodOn(DisposalGuidelineController.class).getAllDisposalGuidelines()).withSelfRel());
    }

    @GetMapping("/disposal-guidelines/{id}")
    public EntityModel<DisposalGuideline> getDisposalGuideline(@PathVariable Long id) {
        DisposalGuideline disposalGuideline = repository.findById(id)
                .orElseThrow(() -> new DisposalGuidelineNotFoundException(id));

        return assembler.toModel(disposalGuideline);
    }

    @PostMapping("/disposal-guidelines")
    public ResponseEntity<EntityModel<DisposalGuideline>> createDisposalGuideline(@RequestBody DisposalGuideline newDisposalGuideline) {

        DisposalGuideline savedDisposalGuideline = repository.save(newDisposalGuideline);

        EntityModel<DisposalGuideline> disposalGuidelineModel = assembler.toModel(savedDisposalGuideline);

        return ResponseEntity.created(disposalGuidelineModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(disposalGuidelineModel);
    }

    @PutMapping("/disposal-guidelines/{id}")
    public DisposalGuideline updateDisposalGuideline(@RequestBody DisposalGuideline newDisposalGuideline, @PathVariable Long id) {
        return repository.findById(id)
                .map(disposalGuideline -> {
                    disposalGuideline.setGuideline(newDisposalGuideline.getGuideline());
                    disposalGuideline.setWasteCategory(newDisposalGuideline.getWasteCategory());
                    return repository.save(disposalGuideline);
                })
                .orElseThrow(() -> new DisposalGuidelineNotFoundException(id));
    }

    @DeleteMapping("/disposal-guidelines/{id}")
    public ResponseEntity<?> deleteDisposalGuideline(@PathVariable Long id) {
        DisposalGuideline disposalGuideline = repository.findById(id)
                .orElseThrow(() -> new DisposalGuidelineNotFoundException(id));
        repository.delete(disposalGuideline);
        return ResponseEntity.noContent().build();
    }



}
