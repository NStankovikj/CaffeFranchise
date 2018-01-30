/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller.lookups;

import com.asseco.controller.AbstractCrudControler;
import com.asseco.dao.AbstractDAO;
import com.asseco.dao.CbCountryDAO;
import com.asseco.lookups.CbCountry;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

/**
 *
 * @author jovana.stefkovska
 */
@ViewScoped
@Named
public class CbCountryController extends AbstractCrudControler<CbCountry> {
   
    private List<CbCountry> list;

    @EJB
    private CbCountryDAO facade;

    @PostConstruct
    public void init() {
        list = facade.findAll();
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected CbCountry prepareNew() {
        CbCountry u = new CbCountry();
        return u;
    }

    @Override
    public LazyDataModel<CbCountry> getLazyDataModel() {
        return super.getLazyDataModel();
    }

    public List<CbCountry> getList() {
        return list;
    }

    public void setList(List<CbCountry> list) {
        this.list = list;
    }

    @Override
    protected AbstractDAO<CbCountry> getDAO() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return facade;
    }
 
}
