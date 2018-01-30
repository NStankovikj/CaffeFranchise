/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.converter;

import com.asseco.dao.CbCountryDAO;
import com.asseco.lookups.CbCountry;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author monika.stojcevska
 */
@FacesConverter(forClass = CbCountry.class)
public class CbCountryConverter extends AbstractELConverter {

    @EJB
    private CbCountryDAO objectDao;

    @Override
    protected String getElName() {
        return "cbCountryController";
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return objectDao.find(value);
    }
}
