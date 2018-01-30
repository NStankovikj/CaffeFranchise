/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.login;

import com.asseco.common.LocaleBean;
import com.asseco.common.MainBean;
import com.asseco.enumeration.UserTypeEnum;
import com.asseco.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "login")
@RequestScoped
public class LoginControler {

    private static final String HOME_URL = "/resources/admin/main.xhtml";

    @Inject
    private MainBean mainBean;

    @Inject
    private LocaleBean localeBean;

    private String username;
    private String password;
    private boolean remember;

    public void submit() {
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, remember));
            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());

            User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
            mainBean.setUserSesion(currentUser);
            if (currentUser.getType() != null && currentUser.getType().equals(UserTypeEnum.RAKOVODITEL_NA_ODELENIE)) {
                mainBean.setRakovoditelUserSesion(true);
            } else {
                mainBean.setRakovoditelUserSesion(false);
            }

            Faces.redirect(mainBean.getAppURL() + HOME_URL);
        } catch (AuthenticationException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("errorLoginMsg"), ""));
            Logger.getLogger(LoginControler.class.getName()).log(Level.INFO, "Unknown user", e);
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UIOException", ""));
            Logger.getLogger(LoginControler.class.getName()).log(Level.INFO, "Unknown user", e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

}
