package com.deloop.user.data.exceptions;


/**
 * Thrown when an invalid email address is encountered.
 */
public class EmailNotFoundException extends ApplicationException {
    public EmailNotFoundException(String message) {
        super(message);
    }
}
