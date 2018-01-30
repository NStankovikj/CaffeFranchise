package com.asseco.dao;


import com.asseco.model.CaffeSubCompany;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CaffeSubCompanyDAO extends AbstractDAO<CaffeSubCompany> {

    public CaffeSubCompanyDAO() {
        super(CaffeSubCompany.class);
    }

    public CaffeSubCompany getCaffeSubCompanyByName(String name) {
        Query q = getEntityManager().createQuery("from CaffeSubCompany c where c.name= :name");
        q.setParameter("name", name);
        List<CaffeSubCompany> list = q.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
