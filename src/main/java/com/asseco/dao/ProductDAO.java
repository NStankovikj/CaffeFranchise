package com.asseco.dao;

import com.asseco.model.Product;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ProductDAO extends AbstractDAO<Product>{

    public ProductDAO() {
        super(Product.class);
    }

    public Product getProductByName(String name) {
        Query q = getEntityManager().createQuery("from Product c where c.name= :name");
        q.setParameter("name", name);
        List<Product> list = q.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
