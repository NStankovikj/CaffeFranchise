package com.asseco.converter;

import com.asseco.dao.OrganizationDAO;
import com.asseco.model.Organization;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Organization.class)
public class OrganizationConverter extends AbstractELConverter{
    @EJB
    private OrganizationDAO objectDao;

    @Override
    protected String getElName() {
        return "organizationController";
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return objectDao.find(value);
    }
}
