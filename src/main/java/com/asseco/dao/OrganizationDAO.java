package com.asseco.dao;

import com.asseco.model.Organization;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class OrganizationDAO extends AbstractDAO<Organization> {

    public OrganizationDAO() {
        super(Organization.class);
    }

    public Organization getOrganizationByName(String name) {
        Query q = getEntityManager().createQuery("from Organization c where c.name= :name");
        q.setParameter("name", name);
        List<Organization> list = q.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
