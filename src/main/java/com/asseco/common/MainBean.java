/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.common;

import com.asseco.dao.RoleDAO;
import com.asseco.dao.UserDAO;
import com.asseco.enumeration.UserTypeEnum;
import com.asseco.model.Role;
import com.asseco.model.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import org.eclipse.jdt.internal.compiler.util.Messages;
import sun.print.ServiceDialog;

/**
 * @author darko.aleksovski
 */
@Named("mainBean")
@SessionScoped
public class MainBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer tabIndex;
    private User userSesion;
    private Boolean rakovoditelUserSesion;


    @EJB
    private UserDAO usrDao;

    @EJB
    private RoleDAO roleDao;

    public void setUsrSesSkin(String usrSesSkin) {
        userSesion.setSkin(usrSesSkin);
        userSesion = usrDao.edit(userSesion);
        usrDao.flush();
    }

    public String getUserNameById(String id) {
        if (usrDao.find(id) != null) {
            return usrDao.find(id).getUsername();
        } else
            return "";
    }

    public Boolean checkRender() {
        if (hasRole(userSesion, "SUPER_ADMIN")){
            return true;
        }else{
            return false;
        }
    }

    public Boolean hasRole(User u,String role) {
        List<Role> rolelist = userSesion.getListRoles();
        Role roleObj = roleDao.getRoleByRolename(role);
        if(roleObj!=null) {
            if (rolelist.contains(roleObj)) {
                return true;
            }
        }
        return false;

    }
    @PostConstruct
    public void init() {
//        if (userSesion.getSkin() == null) {
//            userSesion.setSkin("skin-red");
//        }
    }

    public User getUserSesion() {
        return userSesion;
    }

    public void setUserSesion(User userSesion) {
        this.userSesion = userSesion;
    }

    public Boolean getRakovoditelUserSesion() {
        return rakovoditelUserSesion;
    }

    public void setRakovoditelUserSesion(Boolean rakovoditelUserSesion) {
        this.rakovoditelUserSesion = rakovoditelUserSesion;
    }

    public String getAppURL() {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String scheme = ((HttpServletRequest) request).getScheme();
        String serverName = ((HttpServletRequest) request).getServerName();
        Integer serverPort = ((HttpServletRequest) request).getServerPort();
        String contextPath = ((HttpServletRequest) request).getContextPath();

        String baseURL = scheme + "://" + serverName + ":" + serverPort.toString() + contextPath;

        return baseURL;
    }

    public String getWebSocketAddr() {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String scheme = ((HttpServletRequest) request).getScheme();
        String serverName = ((HttpServletRequest) request).getServerName();
        Integer serverPort = ((HttpServletRequest) request).getServerPort();
        String contextPath = ((HttpServletRequest) request).getContextPath();

        String baseURL = serverName + ":" + serverPort.toString() + contextPath;

        return baseURL + "/notify";
    }

    public Boolean userContainsRole(UserTypeEnum superAdmin) {
        if (userSesion.getType() != null && userSesion.getType().equals(superAdmin)) {
            return true;
        } else {
            return false;
        }
    }

    public Integer getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(Integer tabIndex) {
        this.tabIndex = tabIndex;
    }
    private String returnStar;

    public String returnStar()
    {
        return "*";
    }
}
