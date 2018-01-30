package com.asseco.converter;

import com.asseco.model.Grad;

import javax.faces.convert.FacesConverter;

/**
 *
 * @author atanasij.josifoski
 */
@FacesConverter(forClass = Grad.class)
public class GradoviConverter extends AbstractELConverter{

    @Override
    protected String getElName() {
        return "gradoviController";
    }
    
}
