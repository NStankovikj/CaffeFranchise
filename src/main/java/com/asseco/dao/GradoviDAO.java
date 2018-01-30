/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.dao;

import com.asseco.model.Grad;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * @author atanasij.josifoski
 */
@Stateless
public class GradoviDAO extends AbstractDAO<Grad> {

    public GradoviDAO() {
        super(Grad.class);
    }

    public List<Grad> getGradoviListByCountryId(String id) {
        Query q = getEntityManager().createNativeQuery("select * from GRADOVI g where g.DRZAVI_ID=" + id, Grad.class);
        List<Grad> list = q.getResultList();
        return list;
    }


}
