package com.fixtab.app.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Email is already taken");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}