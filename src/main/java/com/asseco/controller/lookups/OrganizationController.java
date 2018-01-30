package com.asseco.controller.lookups;

import com.asseco.controller.AbstractCrudControler;
import com.asseco.dao.AbstractDAO;
import com.asseco.dao.OrganizationDAO;
import com.asseco.model.Organization;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@ViewScoped
@Named
public class OrganizationController extends AbstractCrudControler<Organization> {

    private List<Organization> organizationList;
    private Organization organization;

    @EJB
    private OrganizationDAO facade;

    @PostConstruct
    public void init() {
        organizationList = facade.findAll();
        organization=new Organization();
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected Organization prepareNew() {
        Organization u = new Organization();
        return u;
    }

    public void saveNewOrganization(){
        organization=facade.edit(organization);
        organizationList.add(organization);
        facade.flush();
        organization = new Organization();
    }

    public void removeOrganization(Organization org){
        facade.remove(org);
        facade.flush();
        organizationList.remove(org);
        organizationList = facade.findAll();
    }

    public void editOrganization(){
        facade.edit(selected);
        facade.flush();
        organizationList = facade.findAll();
        selected = null;
        RequestContext.getCurrentInstance().execute("PF('viewOrganizationDialog').hide()");
    }

    @Override
    public LazyDataModel<Organization> getLazyDataModel() {
        return super.getLazyDataModel();
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    protected AbstractDAO<Organization> getDAO() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return facade;
    }

}

