package com.asseco.dao;

import com.asseco.model.AbstractEntity;
import com.asseco.model.AbstractEntity_;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author nenad.stankovik
 */
@Stateless
public class ValidatorDAO extends AbstractDAO<AbstractEntity> {

    public ValidatorDAO() {
        super(AbstractEntity.class);
    }

    public boolean checkUnique(Class entity, String columnName, Object value, String id) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(entity);
        //Predicate unique

        cq.select(root);
        if (value != null) {
            if (value instanceof String) {
                String trimmedValue = ((String) value).trim();
                if (id != null && !id.isEmpty()) {
                    Predicate checkId = cb.notEqual(root.get(AbstractEntity_.id), id);
                    cq.where(cb.equal(root.get(columnName), trimmedValue), checkId);
                } else {
                    cq.where(cb.equal(root.get(columnName), trimmedValue));
                }
            } else {
                if (id != null && !id.isEmpty()) {
                    Predicate checkId = cb.notEqual(root.get(AbstractEntity_.id), id);
                    cq.where(cb.equal(root.get(columnName), value), checkId);
                } else {
                    cq.where(cb.equal(root.get(columnName), value));
                }
            }
            List<Object> tmpList = getEntityManager().createQuery(cq).setMaxResults(1).getResultList();
            if (tmpList != null && !tmpList.isEmpty()) {
                return false;
            }
        }

        return true;
    }

}
