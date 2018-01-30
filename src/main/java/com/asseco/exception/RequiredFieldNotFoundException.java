package com.asseco.exception;

/**
 * Created by petar.milevski on 5/26/2017.
 */
public class RequiredFieldNotFoundException extends Exception {
    public RequiredFieldNotFoundException(String message) {
        super(message);
    }

    public RequiredFieldNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredFieldNotFoundException(Throwable cause) {
        super(cause);
    }
}
