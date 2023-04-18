package com.fixtab.app.exceptions;


public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(){
        super("Email does not exist");
    }

    public InvalidEmailException(String message){
        super(message);
    }
}
