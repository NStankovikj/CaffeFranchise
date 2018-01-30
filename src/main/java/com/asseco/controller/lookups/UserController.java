/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller.lookups;

import com.asseco.controller.AbstractCrudControler;
import com.asseco.dao.AbstractDAO;
import com.asseco.dao.UserDAO;
import com.asseco.model.User;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author atanasij.josifoski
 */
@ViewScoped
@Named
public class UserController extends AbstractCrudControler<User> {

    @PostConstruct
    public void init() {
        createNew();
    }

    private static final long serialVersionUID = 1L;

    @EJB
    private UserDAO facade;

    @Override
    protected AbstractDAO<User> getDAO() {
        return facade;
    }

    @Override
    protected User prepareNew() {
        User u = new User();
        return u;
    }

    @Override
    public LazyDataModel<User> getLazyDataModel() {
        return super.getLazyDataModel();
    }
}
