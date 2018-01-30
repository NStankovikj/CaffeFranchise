package com.asseco.exception;

/**
 * Created by petar.milevski on 5/26/2017.
 */
public class FirstFieldNotFoundException extends Exception{
    public FirstFieldNotFoundException(String message) {
        super(message);
    }

    public FirstFieldNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FirstFieldNotFoundException(Throwable cause) {
        super(cause);
    }
}
