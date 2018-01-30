package com.asseco.exception;

/**
 * Created by petar.milevski on 4/13/2017.
 */
public class NoFieldFoundException extends Exception {
    public NoFieldFoundException(String message) {
        super(message);
    }

    public NoFieldFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFieldFoundException(Throwable cause) {
        super(cause);
    }

    public NoFieldFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
