package com.enviro.asessment.garde011.KatlehoLephallo.Guidelines;

import org.springframework.stereotype.Service;

@Service
public class DisposalGuidelineService {

    private final DisposalGuidelineRepository repository;

    public DisposalGuidelineService(DisposalGuidelineRepository repository) {
        this.repository = repository;
    }
}
