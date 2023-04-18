package com.fixtab.app.exceptions.responses;

public class ExceptionResponse {
    String message;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
