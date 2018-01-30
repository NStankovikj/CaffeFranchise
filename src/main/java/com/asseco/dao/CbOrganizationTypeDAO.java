package com.asseco.dao;

import com.asseco.lookups.CbOrganizationType;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CbOrganizationTypeDAO extends AbstractDAO<CbOrganizationType> {
    public CbOrganizationTypeDAO() {
        super(CbOrganizationType.class);
    }

    public CbOrganizationType getCbOrganizationTypeByCode(String code) {
        Query q = getEntityManager().createQuery("from CbOrganizationType c where c.code= :code");
        q.setParameter("code", code);
        List<CbOrganizationType> list = q.getResultList();
        if (list.size() == 0)
            return null;
        return list.get(0);
    }
}
