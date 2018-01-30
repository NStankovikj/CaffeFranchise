/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.dao;

import com.asseco.model.Permission;
import com.asseco.model.Role;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author darko.aleksovski
 */
@Stateless
public class PermissionDao extends AbstractDAO<Permission> {
    
    public PermissionDao() {
        super(Permission.class);
    }
    
    public List<Permission> loadPermissionInRole(Role role) {
        Query q = getEntityManager().createNativeQuery("select * from PERMISSION p where p.ID in (select rp.PERMISSION_ID from ROLE r, ROLE_PERMISSION rp where r.ID=rp.ROLE_ID and rp.ROLE_ID='"+role.getId()+"' ) order by p.NAME",Permission.class);
        List<Permission> list = q.getResultList();
        return list;
    }
    
    public List<Permission> loadPermissionNotInRole(Role role) {
        Query q = getEntityManager().createNativeQuery("select * from PERMISSION p where p.ID not in (select rp.PERMISSION_ID from ROLE r, ROLE_PERMISSION rp where r.ID=rp.ROLE_ID and rp.ROLE_ID='"+role.getId()+"' ) order by p.NAME",Permission.class);
        List<Permission> list = q.getResultList();
        return list;
    }
}
