package com.asseco.dao;

import com.asseco.enumeration.UserTypeEnum;
import com.asseco.exception.UserTypeException;
import com.asseco.model.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kiril.micev
 */
@Stateless
public class UserDAO extends AbstractDAO<User> {

    public UserDAO() {
        super(User.class);
    }


    public User getActiveUserByUsernameForLogin(String username) {
        Query q = getEntityManager().createQuery("from User hm where hm.username=:username and hm.active=1");
        q.setParameter("username", username);
        User usr = (User) q.getSingleResult();
        return usr;
    }

    public User getActiveUserByUsername(String username) {
        Query q = getEntityManager().createQuery("from User hm where hm.username=:username and hm.active=1");
        q.setParameter("username", username);
        try {
            User user = (User) q.getSingleResult();
            return user;
        } catch (Exception ex) {
            return null;
        }
    }

    public User getActiveUserByEmail(String email) {
        Query q = getEntityManager().createQuery("from User hm where hm.email=:email and hm.active=1");
        q.setParameter("email", email);
        try {
            User user = (User) q.getSingleResult();
            return user;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean checkEmailIsFree(String email, String notInUserID) {
        String sql = "select u from User u where u.email=:email";
        if (notInUserID != null) {
            sql += " and u.id not in ('" + notInUserID + "')";
        }
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("email", email);

        List<User> n = q.getResultList();
        if (n.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean checkUsernameIsFree(String username, String notInUserID) {
        String sql = "select u from User u where u.username=:username";
        if (notInUserID != null) {
            sql += " and u.id not in ('" + notInUserID + "')";
        }
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("username", username);

        List<User> n = q.getResultList();
        if (n.isEmpty()) {
            return true;
        }
        return false;
    }


    public List<User> getUsersByUserOrganization(User user) {
//        String sql = "select * from USER u where u.ORGANIZATION_ID  = '" + user.getC2h().getC2r21().id +"'";

//        Query q = getEntityManager().createNativeQuery(sql, User.class);
//        List<User> list = q.getResultList();
//        return list;
        return null;
    }
}
