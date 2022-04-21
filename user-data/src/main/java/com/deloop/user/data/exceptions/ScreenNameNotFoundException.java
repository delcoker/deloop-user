package com.deloop.user.data.exceptions;


/**
 * Thrown when an invalid email address is encountered.
 */
public class ScreenNameNotFoundException extends ApplicationException {
    public ScreenNameNotFoundException(String message) {
        super(message);
    }
}
