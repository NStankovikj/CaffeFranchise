package com.asseco.exception;

/**
 * Created by petar.milevski on 5/11/2017.
 */
public class WrongObjectException extends Exception {
    public WrongObjectException(String s) {
        super(s);
    }

    public WrongObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongObjectException(Throwable cause) {
        super(cause);
    }
}
