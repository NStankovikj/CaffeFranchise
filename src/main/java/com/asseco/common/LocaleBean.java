/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.common;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author darko.aleksovski
 */
@Named("localeBean")
@SessionScoped
public class LocaleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Locale locale = new Locale("en", "US");

    public void changeLocaleToMK() {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getViewRoot().setLocale(new Locale("mk", "MK"));
        locale = new Locale("mk", "MK");
    }

    public void changeLocaleToEN() {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getViewRoot().setLocale(new Locale("en", "US"));
        locale = new Locale("en", "US");
    }

    public String getText(String inpKeyMessage) {
        FacesContext fc = FacesContext.getCurrentInstance();

        ResourceBundle bundle = ResourceBundle.getBundle("Bundle", fc.getViewRoot().getLocale());
        String returnValue = bundle.getString(inpKeyMessage);

        return returnValue;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
