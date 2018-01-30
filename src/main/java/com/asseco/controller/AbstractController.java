/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller;

import com.asseco.common.LocaleBean;
import com.asseco.common.MainBean;
import com.asseco.dao.AbstractDAO;
import com.asseco.model.AbstractEntity;
import com.asseco.util.JsfUtil;
import org.omnifaces.util.Messages;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

//import org.apache.shiro.authz.annotation.RequiresRoles;

/**
 *
 * @author kiril.micev
 * @param <T>
 */
public abstract class AbstractController<T extends AbstractEntity> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    protected MainBean mainBean;
    @Inject
    protected LocaleBean localeBean;

    public MainBean getMainBean() {
        return mainBean;
    }

    public void setMainBean(MainBean mainBean) {
        this.mainBean = mainBean;
    }
    
    protected abstract AbstractDAO<T> getDAO();
    protected abstract T prepareNew();

    protected T selected;
    private List<T> items;

    public T getSelected() {
        if (selected == null) {
            selected = prepareNew();
        }
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    public List<T> getItems() {
        if (items == null) {
            items = populateItems();
        }
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public T getItemByKey(Object key) {
        return getDAO().find(key);
    }

    protected List<T> populateItems() {
        return getDAO().findAll();
    }

    protected Logger getLogger() {
        return Logger.getLogger(getClass().getName());
    }

    protected boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public void addMessage(FacesMessage.Severity severity, String message) {
        Messages.addGlobal(new FacesMessage(severity,getLocalisedMessage(message),""));
    }

    public void addMessage(String message) {
        Messages.addGlobal(new FacesMessage(getLocalisedMessage(message),""));
    }

    public void addMessage(String message, String detail) {
        Messages.addGlobal(new FacesMessage(getLocalisedMessage(message), detail));
    }

    public void addMessage(FacesMessage.Severity severity, String message, String detail) {
        Messages.addGlobal(new FacesMessage(severity,getLocalisedMessage(message), detail));
    }

    protected void addMessage(String message, String detail, String cmpId) {
        Messages.add(cmpId, new FacesMessage(cmpId, detail));
    }

    protected void addMessage(FacesMessage.Severity severity, String message, String detail, String cmpId) {
        Messages.add(cmpId, new FacesMessage(severity,cmpId, detail));
    }

    protected String getLocalisedMessage(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        }
        return "???" + key + "???";
    }

    public void refresh() {
        selected = null;
        items = null;
    }

    protected Map getFilter() {
        return null;
    }

    protected boolean validate(Object obj) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<Object>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<Object> cv = iterator.next();
                JsfUtil.addErrorMessage(cv.getRootBeanClass().getSimpleName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
            }
        } else {
            return true;
        }
        return false;
    }

    public void resetEntity(){
        this.getDAO().flush();
        this.selected = this.getDAO().edit(this.selected);

        this.getDAO().flush();
        return;
    }

    public LocaleBean getLocaleBean() {
        return localeBean;
    }

    public void setLocaleBean(LocaleBean localeBean) {
        this.localeBean = localeBean;
    }
}
