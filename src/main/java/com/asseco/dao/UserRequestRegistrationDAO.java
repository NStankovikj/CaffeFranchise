/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.dao;

import com.asseco.model.UserRequestRegistration;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author darko.aleksovski
 */
@Stateless
public class UserRequestRegistrationDAO extends AbstractDAO<UserRequestRegistration> {

    public UserRequestRegistrationDAO() {
        super(UserRequestRegistration.class);
    }
    
    public boolean checkEmailIsFree(String email) {
        Query q = getEntityManager().createQuery("select u from UserRequestRegistration u where u.email=:email");
        q.setParameter("email", email);

        List<UserRequestRegistration> n = q.getResultList();
        if (n.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public boolean checkUsernameIsFree(String username) {
        Query q = getEntityManager().createQuery("select u from UserRequestRegistration u where u.username=:username");
        q.setParameter("username", username);

        List<UserRequestRegistration> n = q.getResultList();
        if (n.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public boolean checKomintentIsFree(String edb,String matBroj) {
        Query q = getEntityManager().createQuery("select u from UserRequestRegistration u where u.edbKompanija=:edb and u.maticenBrojKompanija=:matBroj");
        q.setParameter("edb", edb);
        q.setParameter("matBroj", matBroj);

        List<UserRequestRegistration> n = q.getResultList();
        if (n.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public UserRequestRegistration loadUserByVerifyCode(String verifyCode) {
        Query q = getEntityManager().createQuery("select u from UserRequestRegistration u where u.verify_code=:verifyCode");
        q.setParameter("verifyCode", verifyCode);

        List<UserRequestRegistration> n = q.getResultList();
        if (n.size()==1) {
            return n.get(0);
        }
        return null;
    }
    
}
