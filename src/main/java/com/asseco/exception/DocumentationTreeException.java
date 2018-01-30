package com.asseco.exception;

/**
 * Created by petar.milevski on 5/11/2017.
 */
public class DocumentationTreeException extends Exception {
    public DocumentationTreeException(String s) {
        super(s);
    }

    public DocumentationTreeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentationTreeException(Throwable cause) {
        super(cause);
    }
}
