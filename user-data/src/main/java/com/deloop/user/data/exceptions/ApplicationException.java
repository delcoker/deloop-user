package com.deloop.user.data.exceptions;


/**
 * Created by deloop on 26/04/2020.
 */
public class ApplicationException extends Exception {
    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }
}
