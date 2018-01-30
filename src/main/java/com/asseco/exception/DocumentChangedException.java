package com.asseco.exception;

/**
 * Created by kiril.micev.
 */
public class DocumentChangedException extends Exception {
    public DocumentChangedException(String s) {
        super(s);
    }

    public DocumentChangedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentChangedException(Throwable cause) {
        super(cause);
    }
}
