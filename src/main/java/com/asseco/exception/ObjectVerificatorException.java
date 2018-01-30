package com.asseco.exception;

/**
 * Created by petar.milevski on 5/15/2017.
 */
public class ObjectVerificatorException extends Exception {
    public ObjectVerificatorException(String message) {
        super(message);
    }

    public ObjectVerificatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectVerificatorException(Throwable cause) {
        super(cause);
    }
}
