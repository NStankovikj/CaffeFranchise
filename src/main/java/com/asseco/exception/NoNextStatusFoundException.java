package com.asseco.exception;

/**
 * Created by petar.milevski on 7/7/2017.
 */
public class NoNextStatusFoundException extends Exception {
    public NoNextStatusFoundException(String message) {
        super(message);
    }

    public NoNextStatusFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoNextStatusFoundException(Throwable cause) {
        super(cause);
    }
}
