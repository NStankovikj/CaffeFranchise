package com.asseco.controller.lookups;

import com.asseco.controller.AbstractCrudControler;
import com.asseco.dao.AbstractDAO;
import com.asseco.dao.CaffeSubCompanyDAO;
import com.asseco.dao.EmployeeDAO;
import com.asseco.model.CaffeSubCompany;
import com.asseco.model.Employee;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import sun.awt.CausedFocusEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;


@ViewScoped
@Named
public class EmployeeController extends AbstractCrudControler<Employee>{

    private List<Employee> EmployeesList;
    private Employee Employee;

    @EJB
    private EmployeeDAO facade;

    @EJB
    private CaffeSubCompanyDAO caffeSubCompanyDAO;

    @PostConstruct
    public void init() {
        EmployeesList = facade.findAll();
        Employee=new Employee();
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected Employee prepareNew() {
        Employee u = new Employee();
        return u;
    }

    public void saveNewEmployee(){
        CaffeSubCompany caffeSubCompany = Employee.getCaffeSubCompany();
        caffeSubCompany.getEmployee().add(Employee);

        EmployeesList.add(Employee);

        caffeSubCompany = caffeSubCompanyDAO.edit(caffeSubCompany);
        caffeSubCompanyDAO.flush();

        Employee = new Employee();
        RequestContext.getCurrentInstance().execute("PF('addOrganizationDialog').hide()");
    }

    public void removeEmployee(Employee p){
        CaffeSubCompany caffeSubCompany = p.getCaffeSubCompany();
        caffeSubCompany.getEmployee().remove(p);

        caffeSubCompany = caffeSubCompanyDAO.edit(caffeSubCompany);
        caffeSubCompanyDAO.flush();

        facade.remove(p);
        facade.flush();
        EmployeesList.remove(p);
        EmployeesList = facade.findAll();
    }

    public void editEmployee(){
        Employee e = facade.find(selected.id);
        CaffeSubCompany oldCaffeSubCompany = e.getCaffeSubCompany();
        CaffeSubCompany newCaffeSubCompany = selected.getCaffeSubCompany();

        oldCaffeSubCompany.getEmployee().remove(e);
        newCaffeSubCompany.getEmployee().add(selected);

        selected.setCaffeSubCompany(newCaffeSubCompany);
        caffeSubCompanyDAO.edit(oldCaffeSubCompany);
        caffeSubCompanyDAO.edit(newCaffeSubCompany);

        caffeSubCompanyDAO.flush();
        //facade.edit(selected);
        //facade.flush();
        EmployeesList = facade.findAll();
        selected = null;
        RequestContext.getCurrentInstance().execute("PF('viewEmployeeDialog').hide()");
    }

    @Override
    public LazyDataModel<Employee> getLazyDataModel() {
        return super.getLazyDataModel();
    }

    public List<Employee> getEmployeesList() {
        return EmployeesList;
    }

    public void setEmployeesList(List<Employee> EmployeesList) {
        this.EmployeesList = EmployeesList;
    }

    public Employee getEmployee() {
        return Employee;
    }

    public void setEmployee(Employee Employee) {
        this.Employee = Employee;
    }

    @Override
    protected AbstractDAO<Employee> getDAO() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return facade;
    }

}
