/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.login;

import com.asseco.dao.UserDAO;
import com.asseco.model.Role;
import com.asseco.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kiril.micev
 */
public class MalmedRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        if (user != null) {
            List<Role> roles = user.getListRoles();
            for (Role role : roles) {
                simpleAuthorizationInfo.addRole(role.getName());
                simpleAuthorizationInfo.addStringPermission(role.getName());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (!(token instanceof UsernamePasswordToken)) {
            throw new UnsupportedTokenException();
        }
        UsernamePasswordToken tk = (UsernamePasswordToken) token;
        String username = tk.getUsername();
        try {
            UserDAO huf = InitialContext.doLookup("java:module/UserDAO");
            User user = huf.getActiveUserByUsernameForLogin(username);
            return new SimpleAuthenticationInfo(user, user.getPassword().toCharArray(), user.getUsername());
        } catch (NamingException ex) {
            Logger.getLogger(MalmedRealm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return true;
    }

}
