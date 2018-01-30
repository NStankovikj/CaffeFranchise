package com.asseco.exception;

/**
 * Created by petar.milevski on 5/11/2017.
 */
public class UnknownFlagException extends Exception {
    public UnknownFlagException(String s) {
        super(s);
    }

    public UnknownFlagException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownFlagException(Throwable cause) {
        super(cause);
    }
}
