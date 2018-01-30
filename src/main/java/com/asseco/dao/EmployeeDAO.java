package com.asseco.dao;

import com.asseco.model.Employee;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class EmployeeDAO extends AbstractDAO<Employee>{

    public EmployeeDAO() {
        super(Employee.class);
    }

    public Employee getEmployeeByName(String name) {
        Query q = getEntityManager().createQuery("from Employee c where c.name= :name");
        q.setParameter("name", name);
        List<Employee> list = q.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
