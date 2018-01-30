package com.asseco.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ValidationFailModel implements Serializable {
    private String typeOfError;
    private String instanceOf;
    private String fieldName;
    private String tabName;
    private String actionAfterValidation;

    public ValidationFailModel(String typeOfError, String instanceOf, String fieldName, String tabName, String actionAfterValidation) {
        this.typeOfError = typeOfError;
        this.instanceOf = instanceOf;
        this.fieldName = fieldName;
        this.tabName = tabName;
        this.actionAfterValidation = actionAfterValidation;
    }

    public ValidationFailModel(String dependRequired, String gk4r6a, int r, String text, String string, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
