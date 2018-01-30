package com.asseco.exception;

/**
 * Created by petar.milevski on 5/15/2017.
 */
public class ObjectInspectorException extends Exception {
    public ObjectInspectorException(String message) {
        super(message);
    }

    public ObjectInspectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectInspectorException(Throwable cause) {
        super(cause);
    }
}
