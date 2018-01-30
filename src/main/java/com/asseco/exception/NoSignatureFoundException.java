package com.asseco.exception;

/**
 * Created by petar.milevski on 6/7/2017.
 */
public class NoSignatureFoundException extends Exception {
    public NoSignatureFoundException(String message) {
        super(message);
    }

    public NoSignatureFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSignatureFoundException(Throwable cause) {
        super(cause);
    }
}
