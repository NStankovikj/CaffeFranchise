package com.asseco.exception;

/**
 * Created by petar.milevski on 7/17/2017.
 */
public class UserTypeException extends Exception {
    public UserTypeException(String message) {
        super(message);
    }

    public UserTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserTypeException(Throwable cause) {
        super(cause);
    }
}
