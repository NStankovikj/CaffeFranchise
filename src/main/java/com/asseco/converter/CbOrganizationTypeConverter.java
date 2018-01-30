package com.asseco.converter;

import com.asseco.dao.CbOrganizationTypeDAO;
import com.asseco.lookups.CbOrganizationType;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = CbOrganizationType.class)
public class CbOrganizationTypeConverter extends AbstractELConverter {

    @EJB
    private CbOrganizationTypeDAO objectDao;

    @Override
    protected String getElName() {
        return "cbOrganizationTypeController";
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return objectDao.find(value);
    }
}
