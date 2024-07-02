package com.enviro.asessment.garde011.KatlehoLephallo.Tips;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecyclingTipModelAssembler implements RepresentationModelAssembler<RecyclingTip, EntityModel<RecyclingTip>> {

    @Override
    public EntityModel<RecyclingTip> toModel(RecyclingTip recyclingTip) {
        return EntityModel.of(recyclingTip,
                linkTo(methodOn(RecyclingTipController.class).getRecyclingTip(recyclingTip.getId())).withSelfRel(),
                linkTo(methodOn(RecyclingTipController.class).getAllRecyclingTips()).withRel("recyclingTips"));
    }
}

