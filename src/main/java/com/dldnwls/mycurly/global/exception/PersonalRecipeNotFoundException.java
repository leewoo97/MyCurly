package com.dldnwls.mycurly.global.exception;

public class PersonalRecipeNotFoundException extends RuntimeException{

    public PersonalRecipeNotFoundException() {
        super();
    }

    public PersonalRecipeNotFoundException(String message) {
        super(System.lineSeparator() + "[예외 메세지] " + message);
    }
}
