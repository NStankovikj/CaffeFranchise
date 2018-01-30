/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller.lookups;

import com.asseco.controller.AbstractCrudControler;
import com.asseco.dao.AbstractDAO;
import com.asseco.dao.GradoviDAO;
import com.asseco.dao.CbCountryDAO;
import com.asseco.lookups.CbCountry;
import com.asseco.model.Grad;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author atanasij.josifoski
 */
@ViewScoped
@Named
public class GradoviController extends AbstractCrudControler<Grad> {

    private static final long serialVersionUID = 1L;

    private List<CbCountry> listDrzavi;
    private List<Grad> listGradovi;

    @EJB
    private GradoviDAO facade;

    @EJB
    private CbCountryDAO countryDao;

    @PostConstruct
    private void init() {
        createNew();
        listDrzavi = countryDao.findAll();
        listGradovi = new ArrayList<>();
    }

    public void setGradoviListByCountryId(String id){
        listGradovi = facade.getGradoviListByCountryId(id);
    }

    @Override
    protected AbstractDAO<Grad> getDAO() {
        return facade;
    }

    @Override
    protected Grad prepareNew() {
        Grad gr = new Grad();
        return gr;
    }

    @Override
    public List<Grad> getItems() {
        return super.getItems();
    }

    public List<CbCountry> getListDrzavi() {
        return listDrzavi;
    }

    public void setListDrzavi(List<CbCountry> listDrzavi) {
        this.listDrzavi = listDrzavi;
    }

    public CbCountryDAO getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CbCountryDAO countryDao) {
        this.countryDao = countryDao;
    }

    public List<Grad> getListGradovi() {
        return listGradovi;
    }

    public void setListGradovi(List<Grad> listGradovi) {
        this.listGradovi = listGradovi;
    }
}
