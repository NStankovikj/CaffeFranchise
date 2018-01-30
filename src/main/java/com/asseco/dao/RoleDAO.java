/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.dao;

import com.asseco.model.Role;
import com.asseco.model.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author darko.aleksovski
 */
@Stateless
public class RoleDAO extends AbstractDAO<Role> {

    public RoleDAO() {
        super(Role.class);
    }
    
    public List<Role> loadAllRole() {
        Query q = getEntityManager().createNativeQuery("select * from ROLE r order by r.NAME",Role.class);
        List<Role> list = q.getResultList();
        return list;
    }
    
    public List<Role> loadRoleInUser(User user) {
        Query q = getEntityManager().createNativeQuery("select * from ROLE r where r.ID in (select ur.ROLE_ID from USER u," +
                         " USER_ROLE ur where u.ID=ur.USER_ID and ur.USER_ID='"+user.getId()+"' ) order by r.NAME",Role.class);
        List<Role> list = q.getResultList();
        return list;
    }
    
    public List<Role> loadRoleNotInUser(User user) {
        Query q = getEntityManager().createNativeQuery("select * from ROLE r where r.ID not in (select ur.ROLE_ID from USER u, USER_ROLE ur where u.ID=ur.USER_ID and ur.USER_ID='"+user.getId()+"' ) order by r.NAME",Role.class);
        List<Role> list = q.getResultList();
        return list;
    }
    public Role getRoleByRolename(String roleName) {
        Query q = getEntityManager().createNativeQuery("select * from ROLE r where r.NAME like '" + roleName + "' " ,Role.class);
        List<Role> list = q.getResultList();
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}
