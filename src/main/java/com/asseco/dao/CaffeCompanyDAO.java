package com.asseco.dao;


import com.asseco.model.CaffeCompany;
import com.asseco.model.Employee;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CaffeCompanyDAO extends AbstractDAO<CaffeCompany> {

    public CaffeCompanyDAO() {
        super(CaffeCompany.class);
    }

    public CaffeCompany getCaffeCompanyByName(String name) {
        Query q = getEntityManager().createQuery("from CaffeCompany c where c.name= :name");
        q.setParameter("name", name);
        List<CaffeCompany> list = q.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<Employee> getAllEmployesFromCaffeCompanyById(String id){
     //   Query q = getEntityManager().createQuery("from Employee e , CaffeCompany cc , CaffeSubCompany csb where cc.id = csb.caffeCompany and e.caffeSubCompany = csb.id and cc.id = :id");
        String sql = "select e.* from EMPLOYEE e, CAFFECOMPANY cc, CAFFESUBCOMPANY csb where cc.id = csb.CAFFE_COMPANY  and e.CaffeSubCompany = csb.id  and cc.id = '"+ id +"'";
        Query q = getEntityManager().createNativeQuery(sql, Employee.class);
        List<Employee> list = q.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list;
    }
}
