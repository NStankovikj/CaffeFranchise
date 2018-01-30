/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.converter;

import com.asseco.dao.RoleDAO;
import com.asseco.model.Role;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author monika.stojcevska
 */
@RequestScoped
@FacesConverter(forClass = Role.class, value = "roleConverter")
public class RoleConverter extends AbstractELConverter {

    @EJB
    private RoleDAO roleDAO;

    @Override
    protected String getElName() {
        return "RoleConverter";
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return roleDAO.find(value);
    }

}
