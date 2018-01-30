package com.asseco.exception;

/**
 * @author petar.milevski on 4/5/2017.
 */
public class TableEmptyException extends Exception{

    public TableEmptyException(String message) {
        super(message);
    }

    public TableEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableEmptyException(Throwable cause) {
        super(cause);
    }
}
