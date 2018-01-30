/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.converter;

import com.asseco.controller.AbstractController;
import com.asseco.model.AbstractEntity;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simon
 */
public abstract class AbstractELConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        AbstractController controller = (AbstractController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, getElName());
        return controller.getItemByKey(getKeyValue(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof AbstractEntity) {
            AbstractEntity o = (AbstractEntity) object;
            return String.valueOf(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), getClass().getName()});
            return null;
        }
    }

    protected abstract String getElName();

    protected Object getKeyValue(String key) {
        return key;
    }
    
   

}
