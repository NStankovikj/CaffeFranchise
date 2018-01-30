/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.exception;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kiril.micev
 */
public class InvalidDocumentationException extends Exception {
    private List<String> invalidDocuments;

    public InvalidDocumentationException(String message) {
        super(message);
    }

    public InvalidDocumentationException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public InvalidDocumentationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDocumentationException(Throwable cause) {
        super(cause);
    }

    public List<String> getInvalidDocuments() {
        if(invalidDocuments == null)
            this.invalidDocuments = new ArrayList<>();

        return invalidDocuments;
    }

    public void setInvalidDocuments(List<String> invalidDocuments) {
        this.invalidDocuments = invalidDocuments;
    }
}
