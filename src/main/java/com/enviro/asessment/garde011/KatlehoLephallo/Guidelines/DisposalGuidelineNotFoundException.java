package com.enviro.asessment.garde011.KatlehoLephallo.Guidelines;

public class DisposalGuidelineNotFoundException extends RuntimeException {
    public DisposalGuidelineNotFoundException(Long id) {
        super("Could not find disposal guideline "+ id);
    }
}
