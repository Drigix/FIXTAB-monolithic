package com.fixtab.app.exceptions;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException() {
        super("Account with that email already exists");
    }

    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}