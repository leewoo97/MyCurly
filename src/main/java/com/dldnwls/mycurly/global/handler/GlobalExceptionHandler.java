package com.dldnwls.mycurly.global.handler;

import com.dldnwls.mycurly.global.exception.PersonalRecipeNotFoundException;
import com.dldnwls.mycurly.global.exception.ProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //ProfileNotFoundException 처리
    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<String> handleProfileNotFoundException(ProfileNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //PersonalRecipeNotFoundException 처리
    @ExceptionHandler(PersonalRecipeNotFoundException.class)
    public ResponseEntity<String> handlePersonalRecipeNotFoundException(PersonalRecipeNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
