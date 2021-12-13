package com.deloop.user.data.exceptions;


/**
 * Thrown when an invalid email address is encountered.
 */
public class InvalidEmailException extends ApplicationException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
