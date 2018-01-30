/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller;

import com.asseco.common.LocaleBean;
import com.asseco.dao.*;
import com.asseco.enumeration.UserTypeEnum;
import com.asseco.model.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author darko.aleksovski
 */
@ViewScoped
@Named(value = "listUserRequestRegistrationController")
public class ListUserRequestRegistrationController implements Serializable {

    private static final long serialVersionUID = 1L;

    private UserRequestRegistration userRR;
    private List<Organization> listOrganizaton;
    private List<Role> selectedRoles;
    private List<Role> listRoles;
    private List<UserRequestRegistration> listUserRequestRegistration;

    @EJB
    private UserDAO userDAO;
    @EJB
    private RoleDAO roleDAO;

    @EJB
    private UserRequestRegistrationDAO userRequestRegistrationDAO;

    @EJB
    private OrganizationDAO orgFacade;

    @Inject
    private LocaleBean localeBean;



    @PostConstruct
    public void init() {
        userRR = new UserRequestRegistration();
        listUserRequestRegistration = userRequestRegistrationDAO.findAll();
        listOrganizaton = orgFacade.findAll();
        listRoles = roleDAO.loadAllRole();
        selectedRoles = new ArrayList<>();
    }

    public void assignOrganization() {
        userRequestRegistrationDAO.edit(userRR);
        userRequestRegistrationDAO.flush();
        listUserRequestRegistration = userRequestRegistrationDAO.findAll();
        userRR = new UserRequestRegistration();
    }

    public void assignRoles() {
        userRequestRegistrationDAO.edit(userRR);
        userRequestRegistrationDAO.flush();
        listUserRequestRegistration = userRequestRegistrationDAO.findAll();
        userRR = new UserRequestRegistration();
        selectedRoles = new ArrayList<>();
    }

    public void saveRegistration(UserRequestRegistration urr) {

        if (urr.getVerify_email() != null && urr.getVerify_email()) {
            if (urr.getOrganization() != null) {
                if (!urr.getListRoles().isEmpty()) {

                    User user = new User();
                    user.setUsername(urr.getUsername());
                    user.setPassword(urr.getPassword());
                    user.setEmail(urr.getEmail());
                    user.setListRoles(urr.getListRoles());
                    user.setActive(true);

                    if (urr.getDocuments() != null) {
                        for (Attachment att : urr.getDocuments()) {
                            att.setUserRequestRegistrationID(null);
                            att.setUserID(user);
                        }
                    }

                    userDAO.create(user);

                    userRequestRegistrationDAO.remove(urr);

                    String messageSend = htmlMessageOdobrenaRegistracija(urr);


                    listUserRequestRegistration = userRequestRegistrationDAO.findAll();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("rolesNotAsign"), ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("organizationNotAsign"), ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("registration.emailNotVerify"), ""));
        }
    }

    public void deleteRegistration(UserRequestRegistration urr) {
        userRequestRegistrationDAO.remove(urr);

        String messageSend = htmlMessageOdbienaRegistracija(urr);


        listUserRequestRegistration = userRequestRegistrationDAO.findAll();
    }

    public String htmlMessageOdobrenaRegistracija(UserRequestRegistration urr) {
        String messageSend = "<html>\n"
                + "<body>\n"
                + "<br/>\n"
                + "<p style=\"font-family:Verdana; font-size:12px; font-weight: normal; margin-left: 0.5cm;\">"
                + localeBean.getText("email.greeting") + " "
                + urr.getIme() + " " + urr.getPrezime() + ", "
                + "</p>\n"
                + "<div align=\"justify\"> <p style=\"display:block; font-family:Verdana; font-size:12px; border-color:black; width: 21cm; height: 29.7cm; margin: 0.5cm;\">\n"
                + localeBean.getText("email.odobrenaRegistracija") + " " + urr.getUsername() + "<br /><br />\n"
                + localeBean.getText("email.using.thankYou") + "<br/>\n"
                + localeBean.getText("contact.signature") + "</p></div>\n"
                + "</body>\n"
                + "</html>\n"
                + "</body>\n"
                + "</html>";

        return messageSend;
    }

    public String htmlMessageOdbienaRegistracija(UserRequestRegistration urr) {
        String messageSend = "<html>\n"
                + "<body>\n"
                + "<br/>\n"
                + "<p style=\"font-family:Verdana; font-size:12px; font-weight: normal; margin-left: 0.5cm;\">"
                + localeBean.getText("email.greeting") + " "
                + urr.getIme() + " " + urr.getPrezime() + ", "
                + "</p>\n"
                + "<div align=\"justify\"> <p style=\"display:block; font-family:Verdana; font-size:12px; border-color:black; width: 21cm; height: 29.7cm; margin: 0.5cm;\">\n"
                + localeBean.getText("email.odbienaRegistracija") + "<br /><br />\n"
                + localeBean.getText("email.using.thankYou") + "<br/>\n"
                + localeBean.getText("contact.signature") + "</p></div>\n"
                + "</body>\n"
                + "</html>\n"
                + "</body>\n"
                + "</html>";

        return messageSend;
    }


    public List<UserRequestRegistration> getListUserRequestRegistration() {
        return listUserRequestRegistration;
    }

    public UserRequestRegistration getUserRR() {
        return userRR;
    }

    public void setUserRR(UserRequestRegistration userRR) {
        this.userRR = userRR;
    }

    public List<Organization> getListOrganizaton() {
        return listOrganizaton;
    }

    public void setListOrganizaton(List<Organization> listOrganizaton) {
        this.listOrganizaton = listOrganizaton;
    }

    public List<Role> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<Role> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public List<Role> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<Role> listRoles) {
        this.listRoles = listRoles;
    }
}
