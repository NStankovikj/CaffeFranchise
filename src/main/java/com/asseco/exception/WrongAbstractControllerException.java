package com.asseco.exception;

/**
 * Created by petar.milevski on 4/27/2017.
 */
public class WrongAbstractControllerException extends Exception {
    public WrongAbstractControllerException(String message) {
        super(message);
    }

    public WrongAbstractControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongAbstractControllerException(Throwable cause) {
        super(cause);
    }
}
