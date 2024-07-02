package com.enviro.asessment.garde011.KatlehoLephallo.Tips;

import com.enviro.asessment.garde011.KatlehoLephallo.Guidelines.DisposalGuidelineNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RecyclingTipNotFoundAdvice {

    @ExceptionHandler(RecyclingTipNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String DisposalGuidelineNoFoundHandler(RecyclingTipNotFoundException exception){
        return exception.getMessage();
    }
}
