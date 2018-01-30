/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller;

import com.asseco.common.LocaleBean;
import com.asseco.dao.UserRequestRegistrationDAO;
import com.asseco.model.UserRequestRegistration;
import com.asseco.validator.TYPEValidator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
@Named(value = "verifyRegistrationController")
public class VerifyRegistrationController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private UserRequestRegistrationDAO userRequestRegistrationDAO;

    @Inject
    private LocaleBean localeBean;

    private String status;

    @PostConstruct
    public void init() {

        TYPEValidator typeValidator = new TYPEValidator();

        String varifyCode = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("verifyCode");

        status = localeBean.getText("verifyRegistration.unsuccessfully");

        if (varifyCode != null && !typeValidator.isEmptyString(varifyCode)) {
            UserRequestRegistration userRequestRegistration = userRequestRegistrationDAO.loadUserByVerifyCode(varifyCode);

            if (userRequestRegistration != null) {
                if (!userRequestRegistration.getVerify_email()) {
                    userRequestRegistration.setVerify_email(true);
                    userRequestRegistrationDAO.edit(userRequestRegistration);
                    status = localeBean.getText("verifyRegistration.successfully");
                } else {
                    status = localeBean.getText("verifyRegistration.successfullyVerified");
                }
            }
        }
    }

    public String getStatus() {
        return status;
    }

}
