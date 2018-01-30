package com.asseco.controller.lookups;

import com.asseco.controller.AbstractCrudControler;
import com.asseco.dao.AbstractDAO;
import com.asseco.dao.CbOrganizationTypeDAO;
import com.asseco.lookups.CbOrganizationType;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@ViewScoped
@Named
public class CbOrganizationTypeController extends AbstractCrudControler<CbOrganizationType> {
    private List<CbOrganizationType> list;

    @EJB
    private CbOrganizationTypeDAO facade;

    @PostConstruct
    public void init() {
        list = facade.findAll();
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected CbOrganizationType prepareNew() {
        CbOrganizationType u = new CbOrganizationType();
        return u;
    }

    @Override
    public LazyDataModel<CbOrganizationType> getLazyDataModel() {
        return super.getLazyDataModel();
    }

    public List<CbOrganizationType> getList() {
        return list;
    }

    public void setList(List<CbOrganizationType> list) {
        this.list = list;
    }

    @Override
    protected AbstractDAO<CbOrganizationType> getDAO() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return facade;
    }
}

