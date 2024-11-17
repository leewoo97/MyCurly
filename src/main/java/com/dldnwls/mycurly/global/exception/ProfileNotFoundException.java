package com.dldnwls.mycurly.global.exception;

public class ProfileNotFoundException extends RuntimeException{

    public ProfileNotFoundException() {
        super();
    }

    public ProfileNotFoundException(String message) {
        super(System.lineSeparator() + "[예외 메세지] " + message);
    }
}
