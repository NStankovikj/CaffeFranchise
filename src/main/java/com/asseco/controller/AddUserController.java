/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller;

import com.asseco.common.LocaleBean;
import com.asseco.common.MainBean;
import com.asseco.dao.*;
import com.asseco.model.Role;
import com.asseco.model.User;
import com.asseco.util.Encryption;
import com.asseco.validator.EMAILValidator;
import com.asseco.validator.TYPEValidator;
import org.primefaces.context.RequestContext;

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
@Named(value = "аddUserController")
public class AddUserController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private UserDAO userDAO;
    @EJB
    private RoleDAO roleDAO;
    @EJB
    private UserRequestRegistrationDAO userRequestRegistrationDAO;

    @Inject
    private LocaleBean localeBean;


    @Inject
    private MainBean mainBean;

    private User user;
    private List<User> listUser;
    private List<Role> listRoleIn;
    private List<Role> listRoleOut;

    @PostConstruct
    public void init() {
        createNewObject();

        for (Role role : mainBean.getUserSesion().getListRoles()) {
            if (role.getName().equalsIgnoreCase("SUPER_ADMIN")) {
                listUser = userDAO.findAll();
                break;
            } else if (role.getName().equalsIgnoreCase("COMPANY_ADMIN")) {
                listUser = userDAO.getUsersByUserOrganization(mainBean.getUserSesion());
                break;
            }
        }
        listRoleIn = roleDAO.loadAllRole();
        listRoleOut = new ArrayList<>();
    }

    public Boolean checkRender(String tab) {
        List<Role> rolelist = mainBean.getUserSesion().getListRoles();
        Role role = rolelist.get(0);
        if (tab.equalsIgnoreCase("columnBtns") && role.getName().equalsIgnoreCase("SUPER_ADMIN")) {
            return true;
        } else if (tab.equalsIgnoreCase("btnAddUser") && role.getName().equalsIgnoreCase("SUPER_ADMIN")) {
            return true;
        } else if (tab.equalsIgnoreCase("assignRolePanel") && role.getName().equalsIgnoreCase("SUPER_ADMIN")) {
            return true;
        } else if (tab.equalsIgnoreCase("organization") && !role.getName().equalsIgnoreCase("SUPER_ADMIN")) {
            return true;
        } else {
            return false;
        }
    }

    public void createNewObject() {
        user = new User();
        listRoleIn = roleDAO.loadAllRole();
    }

    public void add() {
        EMAILValidator emailValidator = new EMAILValidator();
        Encryption encryption = new Encryption();
        TYPEValidator typeValidator = new TYPEValidator();
        if (user.getId() != null) {
            if (checkRequiredField(user)) {
                if (emailValidator.checkEMAIL(user.getEmail())) {
                    userDAO.edit(user);
                    userDAO.flush();
                    createNewObject();

                    for (Role role : mainBean.getUserSesion().getListRoles()) {
                        if (role.getName().equalsIgnoreCase("SUPER_ADMIN")) {
                            listUser = userDAO.findAll();
                            break;
                        } else if (role.getName().equalsIgnoreCase("COMPANY_ADMIN")) {
                            listUser = userDAO.getUsersByUserOrganization(mainBean.getUserSesion());
                            break;
                        }
                    }

                    RequestContext.getCurrentInstance().execute("PF('addUserDialog').hide()");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, localeBean.getText("messageSuccessAdd"), ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("requiredMessageEmail"), ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("requiredMessageAll"), ""));
            }
        } else {
            if (checkRequiredField(user)) {
                if (emailValidator.checkEMAIL(user.getEmail())) {
                    if (userDAO.checkEmailIsFree(user.getEmail(), null) && userRequestRegistrationDAO.checkEmailIsFree(user.getEmail())) {
                        if (userDAO.checkUsernameIsFree(user.getUsername(), null) && userRequestRegistrationDAO.checkUsernameIsFree(user.getUsername())) {

                            String pass = encryption.getRandomAlphaNum(8);
                            user.setPassword(encryption.cryptReverseWithSha256(pass));
                            user.setActive(true);
                            userDAO.create(user);

                            String messageSend = htmlMessage(user, pass);

                            createNewObject();
                            listRoleOut = new ArrayList<>();
                            for (Role role : mainBean.getUserSesion().getListRoles()) {
                                if (role.getName().equalsIgnoreCase("SUPER_ADMIN")) {
                                    listUser = userDAO.findAll();
                                    break;
                                } else if (role.getName().equalsIgnoreCase("COMPANY_ADMIN")) {
                                    listUser = userDAO.getUsersByUserOrganization(mainBean.getUserSesion());
                                    break;
                                }
                            }
                            RequestContext.getCurrentInstance().execute("PF('addUserDialog').hide()");

                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, localeBean.getText("messageSuccessAdd"), ""));
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("usernameIsUsed"), ""));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("emailIsUsed"), ""));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("requiredMessageEmail"), ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("requiredMessageAll"), ""));
            }
        }
    }


    public void deleteUser(User u) {
        userDAO.remove(u);
        userDAO.flush();
        for (Role role : mainBean.getUserSesion().getListRoles()) {
            if (role.getName().equalsIgnoreCase("SUPER_ADMIN")) {
                listUser = userDAO.findAll();
                break;
            } else if (role.getName().equalsIgnoreCase("COMPANY_ADMIN")) {
                listUser = userDAO.getUsersByUserOrganization(mainBean.getUserSesion());
                break;
            }
        }
    }

    public boolean checkRequiredField(User user) {

        TYPEValidator typeValidator = new TYPEValidator();

        if (typeValidator.isEmptyString(user.getUsername())) {
            return false;
        }
        if (typeValidator.isEmptyString(user.getEmail())) {
            return false;
        }
        return true;
    }

    public String htmlMessage(User user, String pass) {
        String messageSend = "<html>\n"
                + "<body>\n"
                + "<br/>\n"
                + "<p style=\"font-family:Verdana; font-size:12px; font-weight: normal; margin-left: 0.5cm;\">"
                + localeBean.getText("email.greeting") + " "
//                + user.getC2h().getC2r12() + " " + user.getC2h().getC2r14() + ", "
                + "</p>\n<p>"
                + "<p style=\"font-family:Verdana; font-size:12px; font-weight: normal; margin-left: 0.5cm;\">"
                + localeBean.getText("email.using.thankYou")
                + "</p>\n<p>"
                + "<div align=\"justify\"> <p style=\"display:block; font-family:Verdana; font-size:12px; border-color:black; width: 21cm; height: 29.7cm; margin: 0.5cm;\">\n"
                + localeBean.getText("email.infomsgforregistration1") + "<br /><br />\n"
                + localeBean.getText("email.username") + ": <i>" + user.getUsername() + "</i><br/>\n"
                + localeBean.getText("email.password") + ": <i>" + pass + "</i><br/><br/>\n"
                + localeBean.getText("contact.signature") + "</p></div>\n"
                + "</body>\n"
                + "</html>\n"
                + "</body>\n"
                + "</html>";

        return messageSend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        listRoleOut = user.getListRoles();
    }


    public List<Role> getListRoleIn() {
        return listRoleIn;
    }

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public List<Role> getListRoleOut() {
        return listRoleOut;
    }

    public void setListRoleOut(List<Role> listRoleOut) {
        this.listRoleOut = listRoleOut;
    }

    public MainBean getMainBean() {
        return mainBean;
    }

    public void setMainBean(MainBean mainBean) {
        this.mainBean = mainBean;
    }
}
