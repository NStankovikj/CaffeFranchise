/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller;

import com.asseco.common.LocaleBean;
import com.asseco.common.MainBean;
import com.asseco.dao.AbstractDAO;
import com.asseco.dao.UserDAO;
import com.asseco.model.User;
import com.asseco.validator.EMAILValidator;
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
 */
@ViewScoped
@Named(value = "myProfileController")
public class MyProfileController extends AbstractCrudControler<User>  {

    private static final long serialVersionUID = 1L;

    @EJB
    private UserDAO userDAO;
    @Inject
    private MainBean mainBean;
    @Inject
    private LocaleBean localeBean;

    private User user;

    @PostConstruct
    public void init() {
        user = mainBean.getUserSesion();
    }

    public void SavePrimarySource(){
        userDAO.edit(user);
        userDAO.flush();
        user = userDAO.getActiveUserByUsername(user.getUsername());
        addMessage("saved");
        mainBean.setUserSesion(user);

    }
    public void update() {

        EMAILValidator emailValidator = new EMAILValidator();
        TYPEValidator typeValidator = new TYPEValidator();

        if (user.getUsername().equals(mainBean.getUserSesion().getUsername())) {
                if (emailValidator.checkEMAIL(user.getEmail())) {
                    if (userDAO.checkEmailIsFree(user.getEmail(), user.getId())) {
                        user = userDAO.edit(user);
                        userDAO.flush();
                        mainBean.setUserSesion(user);

                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, localeBean.getText("messageSuccessEdit"), ""));

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("emailIsUsed"), ""));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("requiredMessageEmail"), ""));
                }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("usernameNotEqual"), ""));
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected AbstractDAO<User> getDAO() {
        return null;
    }

    @Override
    protected User prepareNew() {
        return null;
    }
}
