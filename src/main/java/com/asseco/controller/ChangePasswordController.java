/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller;

import com.asseco.common.LocaleBean;
import com.asseco.common.MainBean;
import com.asseco.dao.UserDAO;
import com.asseco.model.User;
import com.asseco.util.Encryption;
import com.asseco.validator.TYPEValidator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 *
 * @author darko.aleksovski
 */
@ViewScoped
@Named(value = "changePasswordController")
public class ChangePasswordController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private UserDAO userDAO;

    @Inject
    private MainBean mainBean;

    @Inject
    private LocaleBean localeBean;

    private String oldPass;
    private String newPass;
    private String confPass;

    @PostConstruct
    public void init() {

    }

    public void changepass() {
        User userSesion = mainBean.getUserSesion();
        Encryption encryption = new Encryption();
        TYPEValidator typeValidator = new TYPEValidator();

        if (!typeValidator.isEmptyString(oldPass) && !typeValidator.isEmptyString(newPass) && !typeValidator.isEmptyString(confPass)) {
            if (userSesion.getPassword().equals(encryption.cryptReverseWithSha256(oldPass))) {
                if (newPass.equals(confPass)) {
                    userSesion.setPassword(encryption.cryptReverseWithSha256(newPass));
                    userSesion = userDAO.edit(userSesion);
                    userDAO.flush();
                    
                    mainBean.setUserSesion(userSesion);

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, localeBean.getText("messageSuccessEdit"), ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("changePassword.messageConfirm"), ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("changePassword.messageCurrent"), ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("changePasswordRequiredField"), ""));
        }
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfPass() {
        return confPass;
    }

    public void setConfPass(String confPass) {
        this.confPass = confPass;
    }

}
