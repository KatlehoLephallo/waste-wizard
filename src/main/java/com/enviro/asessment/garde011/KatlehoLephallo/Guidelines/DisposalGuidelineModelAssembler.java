package com.enviro.asessment.garde011.KatlehoLephallo.Guidelines;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DisposalGuidelineModelAssembler implements RepresentationModelAssembler<DisposalGuideline, EntityModel<DisposalGuideline>> {

    @Override
    public EntityModel<DisposalGuideline> toModel(DisposalGuideline disposalGuideline) {
        return EntityModel.of(disposalGuideline,
                linkTo(methodOn(DisposalGuidelineController.class).getDisposalGuideline(disposalGuideline.getId())).withSelfRel(),
                linkTo(methodOn(DisposalGuidelineController.class).getAllDisposalGuidelines()).withRel("disposalGuidelines"));
    }
}


