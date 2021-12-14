package com.deloop.user.data.exceptions;


/**
 * Thrown when an invalid email address is encountered.
 */
public class EmailInvalidException extends ApplicationException {
    public EmailInvalidException(String message) {
        super(message);
    }
}
