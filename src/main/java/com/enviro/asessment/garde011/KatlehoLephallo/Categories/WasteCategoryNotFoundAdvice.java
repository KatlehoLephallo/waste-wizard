package com.enviro.asessment.garde011.KatlehoLephallo.Categories;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WasteCategoryNotFoundAdvice {

    @ExceptionHandler(WasteCategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String wasteCategoryNotFoundHandler(WasteCategoryNotFoundException exception){
        return exception.getMessage();
    }

}
