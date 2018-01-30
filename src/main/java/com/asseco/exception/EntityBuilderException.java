package com.asseco.exception;

/**
 * Created by petar.milevski on 5/15/2017.
 */
public class EntityBuilderException extends Exception {
    public EntityBuilderException(String message) {
        super(message);
    }

    public EntityBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityBuilderException(Throwable cause) {
        super(cause);
    }
}
