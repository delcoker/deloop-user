package com.deloop.user.data.exceptions;


/**
 * Thrown when the given Address doesn't exist.
 */
public class NoSuchAddressException extends EntityNotFoundException {
    public NoSuchAddressException(String message) {
        super(message);
    }
}
