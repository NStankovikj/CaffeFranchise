package com.asseco.controller.lookups;

import com.asseco.controller.AbstractCrudControler;
import com.asseco.dao.AbstractDAO;
import com.asseco.dao.CaffeCompanyDAO;
import com.asseco.model.CaffeCompany;
import com.asseco.model.CaffeSubCompany;
import com.asseco.model.Employee;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class CaffeCompanyController extends AbstractCrudControler<CaffeCompany>{

    private List<CaffeCompany> caffeCompanyList;
    private CaffeCompany caffeCompany;

    @EJB
    private CaffeCompanyDAO facade;

    @PostConstruct
    public void init() {
        caffeCompanyList = facade.findAll();
        caffeCompany = new CaffeCompany();
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected CaffeCompany prepareNew() {
        CaffeCompany u = new CaffeCompany();
        return u;
    }

    public void saveNewCaffeCompany() {
        caffeCompanyList.add(caffeCompany);
        facade.edit(caffeCompany);
        facade.flush();
        caffeCompany = new CaffeCompany();
    }

    public void removeCaffeCompany(CaffeCompany org) {
        facade.remove(org);
        facade.flush();
        caffeCompanyList.remove(org);
        caffeCompanyList = facade.findAll();
    }

    public void editCaffeCompany() {
        facade.edit(selected);
        facade.flush();
        caffeCompanyList = facade.findAll();
        selected = null;
        RequestContext.getCurrentInstance().execute("PF('viewOrganizationDialog').hide()");
    }

    @Override
    public LazyDataModel<CaffeCompany> getLazyDataModel() {
        return super.getLazyDataModel();
    }

    public List<Employee> getEmployeeListtByCaffeCompanyId(String id){
        List<Employee> employeeList1 = new ArrayList<>();
        employeeList1 = facade.getAllEmployesFromCaffeCompanyById(id);
        return employeeList1;
    }


    public List<CaffeCompany> getCaffeCompanyList() {
        return caffeCompanyList;
    }

    public void setCaffeCompanyList(List<CaffeCompany> caffeCompanyList) {
        this.caffeCompanyList = caffeCompanyList;
    }

    public CaffeCompany getCaffeCompany() {
        return caffeCompany;
    }

    public void setCaffeCompany(CaffeCompany caffeCompany) {
        this.caffeCompany = caffeCompany;
    }

    public CaffeCompanyDAO getFacade() {
        return facade;
    }

    public void setFacade(CaffeCompanyDAO facade) {
        this.facade = facade;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    protected AbstractDAO<CaffeCompany> getDAO() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return facade;
    }
}
