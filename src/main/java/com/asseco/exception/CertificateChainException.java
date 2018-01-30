package com.asseco.exception;

/**
 * Created by kiril.micev.
 */
public class CertificateChainException extends Exception {
    public CertificateChainException(String s) {
        super(s);
    }

    public CertificateChainException(String message, Throwable cause) {
        super(message, cause);
    }

    public CertificateChainException(Throwable cause) {
        super(cause);
    }
}
