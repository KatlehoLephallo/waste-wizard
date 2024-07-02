package com.enviro.asessment.garde011.KatlehoLephallo.Tips;

public class RecyclingTipNotFoundException extends RuntimeException{

    RecyclingTipNotFoundException(Long id){
        super("Could not find Recycling Tip "+ id);
    }
}
