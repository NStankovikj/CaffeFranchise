/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.dao;

import com.asseco.dao.AbstractDAO;
import com.asseco.lookups.CbCountry;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author nenad.stankovik
 */
@Stateless
public class CbCountryDAO extends AbstractDAO<CbCountry>{
    
  public CbCountryDAO() {
        super(CbCountry.class);
    }
  
    public CbCountry getCbCountryByIso(String iso) {
        Query q = getEntityManager().createQuery("from CbCountry c where c.iso= :iso");
        q.setParameter("iso", iso);
        List<CbCountry> list = q.getResultList();
        if (list.size() == 0)
            return null;
        return list.get(0);
    }    
}
