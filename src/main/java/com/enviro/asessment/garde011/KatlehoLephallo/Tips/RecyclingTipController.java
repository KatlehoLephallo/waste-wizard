package com.enviro.asessment.garde011.KatlehoLephallo.Tips;

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
public class RecyclingTipController {

    private final RecyclingTipsRepository repository;
    private final RecyclingTipModelAssembler assembler;

    public RecyclingTipController(RecyclingTipsRepository repository, RecyclingTipModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/recycling-tips")
    public CollectionModel<EntityModel<RecyclingTip>> getAllRecyclingTips() {
        // show all tips

        List<EntityModel<RecyclingTip>> recyclingTips = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(recyclingTips, linkTo(methodOn(RecyclingTipController.class).getAllRecyclingTips()).withSelfRel());
    }

    @GetMapping("/recycling-tips/{id}")
    public EntityModel<RecyclingTip> getRecyclingTip(@PathVariable Long id) {
        // get tip by id

        RecyclingTip recyclingTip = repository.findById(id)
                .orElseThrow(() -> new RecyclingTipNotFoundException(id));

        return assembler.toModel(recyclingTip);
    }

    @PostMapping("/recycling-tips")
    public ResponseEntity<?> createRecyclingTip(@RequestBody RecyclingTip newRecyclingTip) {
        // creating new tip

        EntityModel<RecyclingTip> entityModel = assembler.toModel(repository.save(newRecyclingTip));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/recycling-tips/{id}")
    public RecyclingTip updateRecyclingTip(@RequestBody RecyclingTip newRecyclingTip, @PathVariable Long id) {
        // updating an existing tip

        return repository.findById(id)
                .map(recyclingTip -> {
                    recyclingTip.setTip(newRecyclingTip.getTip());
                    recyclingTip.setCategory(newRecyclingTip.getCategory());
                    return repository.save(recyclingTip);
                })
                .orElseThrow(() -> new RecyclingTipNotFoundException(id));
    }

    @DeleteMapping("/recycling-tips/{id}")
    public ResponseEntity<?> deleteRecyclingTip(@PathVariable Long id) {
        RecyclingTip recyclingTip = repository.findById(id)
                .orElseThrow(() -> new RecyclingTipNotFoundException(id));
        repository.delete(recyclingTip);
        return ResponseEntity.noContent().build();
    }

}

