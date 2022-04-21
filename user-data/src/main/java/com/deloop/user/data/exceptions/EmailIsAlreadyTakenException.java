package com.deloop.user.data.exceptions;


/**
 * Thrown when the desired email address is already taken.
 */
public class EmailIsAlreadyTakenException extends EntityAlreadyExistsException {
    public EmailIsAlreadyTakenException(String message) {
        super(message);
    }
}
