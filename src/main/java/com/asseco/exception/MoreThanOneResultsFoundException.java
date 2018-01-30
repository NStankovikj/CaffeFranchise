package com.asseco.exception;

/**
 * @author petar.milevski on 4/5/2017.
 */
public class MoreThanOneResultsFoundException extends Exception{

    public MoreThanOneResultsFoundException(String message) {
        super(message);
    }

    public MoreThanOneResultsFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoreThanOneResultsFoundException(Throwable cause) {
        super(cause);
    }
}
