package com.asseco.converter;


import com.asseco.dao.CaffeCompanyDAO;
import com.asseco.model.CaffeCompany;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = CaffeCompany.class)
public class CaffeCompanyConverter extends AbstractELConverter{

    @EJB
    private CaffeCompanyDAO objectDao;

    @Override
    protected String getElName() {
        return "caffeCompanyController";
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return objectDao.find(value);
    }
}
