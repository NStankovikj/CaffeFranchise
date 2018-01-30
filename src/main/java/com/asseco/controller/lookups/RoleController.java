/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller.lookups;

import com.asseco.controller.AbstractCrudControler;
import com.asseco.dao.AbstractDAO;
import com.asseco.dao.RoleDAO;
import com.asseco.dao.UserDAO;
import com.asseco.model.Role;
import com.asseco.model.User;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

/**
 * @author atanasij.josifoski
 */
@ViewScoped
@Named
public class RoleController extends AbstractCrudControler<Role> {
    private List<Role> roleList;

    private static final long serialVersionUID = 1L;

    @EJB
    private RoleDAO facade;

    @Override
    protected AbstractDAO<Role> getDAO() {
        return facade;
    }

    @PostConstruct
    public void init() {
        roleList = facade.findAll();
    }

    @Override
    protected Role prepareNew() {
        Role r = new Role();
        return r;
    }

    @Override
    public LazyDataModel<Role> getLazyDataModel() {
        return super.getLazyDataModel();
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
