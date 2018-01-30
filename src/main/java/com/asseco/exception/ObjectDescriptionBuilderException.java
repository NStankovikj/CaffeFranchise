package com.asseco.exception;

/**
 * Created by petar.milevski on 5/15/2017.
 */
public class ObjectDescriptionBuilderException extends Exception {
    public ObjectDescriptionBuilderException(String message) {
        super(message);
    }

    public ObjectDescriptionBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectDescriptionBuilderException(Throwable cause) {
        super(cause);
    }
}
