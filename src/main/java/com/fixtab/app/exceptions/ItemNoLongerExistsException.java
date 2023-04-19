package com.fixtab.app.exceptions;

public class ItemNoLongerExistsException extends RuntimeException {

    public ItemNoLongerExistsException() {
        super("Item no longer exist");
    }

    public ItemNoLongerExistsException(String message) {
        super(message);
    }
}
