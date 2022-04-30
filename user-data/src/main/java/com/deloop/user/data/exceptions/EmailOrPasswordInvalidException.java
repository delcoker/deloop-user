package com.deloop.user.data.exceptions;


/**
 * Thrown when an invalid email address is encountered.
 */
public class EmailOrPasswordInvalidException extends ApplicationException {
    public EmailOrPasswordInvalidException(String message) {
        super(message);
    }
}
