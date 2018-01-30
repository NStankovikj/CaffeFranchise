package com.asseco.converter;

import com.asseco.dao.CaffeSubCompanyDAO;
import com.asseco.model.CaffeSubCompany;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = CaffeSubCompany.class)
public class CaffeSubCompanyConverter extends AbstractELConverter{

    @EJB
    private CaffeSubCompanyDAO objectDao;

    @Override
    protected String getElName() {
        return "caffeSubCompanyController";
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return objectDao.find(value);
    }
}
