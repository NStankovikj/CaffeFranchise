package com.asseco.controller.lookups;


import com.asseco.controller.AbstractCrudControler;
import com.asseco.dao.AbstractDAO;
import com.asseco.dao.CaffeCompanyDAO;
import com.asseco.dao.CaffeSubCompanyDAO;
import com.asseco.model.CaffeCompany;
import com.asseco.model.CaffeSubCompany;
import com.asseco.model.Employee;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

import static com.asseco.model.CaffeSubCompany.*;

@ViewScoped
@Named
public class CaffeSubCompanyController extends AbstractCrudControler<CaffeSubCompany> {

    private List<CaffeSubCompany> caffeSubCompanyList;
    private CaffeSubCompany caffeSubCompany;
    @EJB
    private CaffeSubCompanyDAO facade;

    @EJB
    private CaffeCompanyDAO caffeCompanyDAO;

    @PostConstruct
    public void init() {
        caffeSubCompanyList = facade.findAll();
        caffeSubCompany = new CaffeSubCompany();
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected CaffeSubCompany prepareNew() {
        CaffeSubCompany u = new CaffeSubCompany();
        return u;
    }

    public void saveNewCaffeSubCompany() {
        CaffeCompany caffeCompany = caffeSubCompany.getCaffeCompany();
        caffeCompany.getCaffeSubCompany().add(caffeSubCompany);

        caffeSubCompanyList.add(caffeSubCompany);

        caffeCompany = caffeCompanyDAO.edit(caffeCompany);
        caffeCompanyDAO.flush();

        caffeCompany = new CaffeCompany();
        RequestContext.getCurrentInstance().execute("PF('addOrganizationDialog').hide()");
    }

    public void removeCaffeSubCompany(CaffeSubCompany org) {
        facade.remove(org);
        facade.flush();
        caffeSubCompanyList.remove(org);
        caffeSubCompanyList = facade.findAll();
    }

    public void editCaffeSubCompany() {
        facade.edit(selected);
        facade.flush();
        caffeSubCompanyList = facade.findAll();
        selected = null;
        RequestContext.getCurrentInstance().execute("PF('viewOrganizationDialog').hide()");
    }

    @Override
    public LazyDataModel<CaffeSubCompany> getLazyDataModel() {
        return super.getLazyDataModel();
    }

    public List<CaffeSubCompany> getCaffeSubCompanyList() {
        return caffeSubCompanyList;
    }

    public void setCaffeSubCompanyList(List<CaffeSubCompany> organizationList) {
        this.caffeSubCompanyList = organizationList;
    }

    public CaffeSubCompany getCaffeSubCompany() {
        return caffeSubCompany;
    }

    public void setCaffeSubCompany(CaffeSubCompany organization) {
        this.caffeSubCompany = organization;
    }

    @Override
    protected AbstractDAO<CaffeSubCompany> getDAO() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return facade;
    }
}
