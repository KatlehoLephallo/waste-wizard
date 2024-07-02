package com.enviro.asessment.garde011.KatlehoLephallo.Guidelines;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DisposalGuidelineNotFoundAdvice {

    @ExceptionHandler(DisposalGuidelineNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String DisposalGuidelineNoFoundHandler(DisposalGuidelineNotFoundException exception){
        return exception.getMessage();
    }
}
