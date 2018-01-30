package com.asseco.exception;

/**
 * @author petar.milevski on 4/5/2017.
 */
public class NoResultsFoundException extends Exception{

    public NoResultsFoundException(String message) {
        super(message);
    }

    public NoResultsFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoResultsFoundException(Throwable cause) {
        super(cause);
    }
}
