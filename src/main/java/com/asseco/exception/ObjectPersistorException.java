package com.asseco.exception;

/**
 * Created by petar.milevski on 5/15/2017.
 */
public class ObjectPersistorException extends Exception {
    public ObjectPersistorException(String message) {
        super(message);
    }

    public ObjectPersistorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectPersistorException(Throwable cause) {
        super(cause);
    }
}
