/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.dao;

import com.asseco.common.MainBean;
import com.asseco.enumeration.CStateEnum;
import com.asseco.model.AbstractEntity;
import com.asseco.model.AbstractEntity_;
import com.asseco.util.JsfUtil;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.CursoredStream;
import org.eclipse.persistence.sessions.CopyGroup;
import org.primefaces.model.SortOrder;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kiril.micev
 * @param <T>
 */
public abstract class AbstractDAO<T> {


    @PersistenceContext(unitName = "CaffeFranchise")
    private EntityManager em;


    @Inject
    private MainBean mb;

    public final Class<T> entityClass;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "{0}.{1} {2}", new Object[]{cv.getRootBeanClass().getName(), cv.getPropertyPath(), cv.getMessage()});
                JsfUtil.addErrorMessage(cv.getRootBeanClass().getSimpleName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
            }
        } else {
            if (mb.getUserSesion() != null) {
                if (entity instanceof AbstractEntity) {
                    AbstractEntity abs = (AbstractEntity) entity;
                    abs.setCreatedBy(mb.getUserSesion().getId());
                    ((AbstractEntity) entity).setCreatedBy(mb.getUserSesion().getId());
                }
            }
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "CREATE: " + entity.toString());
            getEntityManager().persist(entity);
        }
    }

//    @RequiresRoles("ROLE_PAYROLL")
    public T edit(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "{0}.{1} {2}", new Object[]{cv.getRootBeanClass().getName(), cv.getPropertyPath(), cv.getMessage()});
                JsfUtil.addErrorMessage(cv.getRootBeanClass().getSimpleName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
            }
            return null;
        } else {
            if (mb.getUserSesion() != null) {
                if (entity instanceof AbstractEntity) {
                    AbstractEntity abs = (AbstractEntity) entity;
                    abs.setModifiedBy(mb.getUserSesion().getId());
                    ((AbstractEntity) entity).setModifiedBy(mb.getUserSesion().getId());
                }
            }
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "EDIT: " + entity.toString());
            return getEntityManager().merge(entity);
        }

    }

//    @RequiresRoles("ADMIN")
    public void remove(T entity) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "REMOVE: " + entity.toString());
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "FIND: " + id.toString());
        return (T) getEntityManager().find(entityClass, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public T findNewT(Object id) {
        return (T) getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        Query q = getEntityManager().createQuery("from " + getEntityClass().getSimpleName() + " clen where (clen.cState in :stateEnum or clen.cState is null and clen.isActive != 0 or clen.isActive is null) order by clen.dateCreated");
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, q.toString());
        q.setParameter("stateEnum", visibleEnums());
        return q.getResultList();

    }

    public List<T> findRange(int[] range, Map<String, Object> filters, String sortField, SortOrder sortOrder) {
        return findRange(range, filters, sortField, sortOrder, null);
    }

    //TODO @kiril.micev (clen.cState in :stateEnum or clen.cState is null)
    public List<T> findRange(int[] range, Map<String, Object> filter, String sortField, SortOrder sortOrder, Map<String, Object> eqmap) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(entityClass);
        Expression<String> parentExpression = root.get(AbstractEntity_.cState);
        Predicate inCState = parentExpression.in(visibleEnums());
        Predicate isNullCState = cb.isNull(root.get(AbstractEntity_.cState));
        Predicate dis = cb.or(inCState, isNullCState);
        cq.select(root);
        List<Predicate> preds = createPredicateFilter(filter, eqmap, root, cb);
        if (preds == null) {
            return new ArrayList<>();//prazen predikant za custom join
        }
        preds.add(dis);
        cq.where(preds.toArray(new Predicate[]{}));

        if (sortField != null) {
            switch (sortOrder) {
                case ASCENDING:
                    cq.orderBy(cb.asc(getPath(root, sortField)));
                    break;
                case DESCENDING:
                    cq.orderBy(cb.desc(getPath(root, sortField)));
                    break;
                default:
                //noop
            }
        }

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return q.getResultList();
    }

    private Path<Object> getPath(Root root, String name) {
        String[] arr = name.split("\\.");
        Path<Object> path = root.get(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            path = path.get(arr[i]);
        }
        return path;
    }

    public int count(Map<String, Object> filter) {
        return count(filter, null);
    }

    //TODO @kiril.micev
    public int count(Map<String, Object> filter, Map<String, Object> eqmap) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root rt = cq.from(entityClass);
        Expression<String> parentExpression = rt.get(AbstractEntity_.cState);
        Predicate inCState = parentExpression.in(visibleEnums());
        Predicate isNullCState = cb.isNull(rt.get(AbstractEntity_.cState));
        Predicate dis = cb.or(inCState, isNullCState);
        cq.select(cb.count(rt));
        List<Predicate> preds = createPredicateFilter(filter, eqmap, rt, cb);
        if (preds == null) {
            return 0; //prazen predikant za custom join
        }
        preds.add(dis);
        cq.where(preds.toArray(new Predicate[]{}));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return ((Long) q.getSingleResult()).intValue();
    }

    protected List<Predicate> createPredicateFilter(Map<String, Object> filter, Map<String, Object> eqmap, Root rt, CriteriaBuilder cb) {
        List<Predicate> preds = new ArrayList<>();
        if (filter != null && !filter.isEmpty()) {
            Set<String> keys = filter.keySet();
            for (String key : keys) {
                Object val = filter.get(key);
                if (val != null && val instanceof String) {
                    String stringVal = "%" + String.valueOf(val).toLowerCase() + "%";
                    preds.add(cb.like(cb.lower(rt.get(key)), stringVal));
                } else if (val != null) {
                    preds.add(cb.equal(rt.get(key), val));
                } else {
                    preds.add(cb.isNull(rt.get(key)));
                }
            }
        }

        if (eqmap != null && !eqmap.isEmpty()) {
            Set<String> keys = eqmap.keySet();
            for (String key : keys) {
                preds.add(cb.equal(rt.get(key), eqmap.get(key)));
            }
        }
        return preds;
    }

    public CursoredStream getAllScrollable() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        Root root = cq.from(entityClass);
        Expression<String> parentExpression = root.get(AbstractEntity_.cState);
        Predicate inCState = parentExpression.in(visibleEnums());
        Predicate isNullCState = cb.isNull(root.get(AbstractEntity_.cState));
        Predicate dis = cb.or(inCState, isNullCState);
        cq.where(dis);
        cq.select(root);
        Query q = getEntityManager().createQuery(cq);
        q.setHint("eclipselink.cursor", true);
        return (CursoredStream) q.getSingleResult();
    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }

    protected List<Predicate> getAdditionalPredicates(Root root) {
        return Collections.EMPTY_LIST;
    }

//    private void addAdditionalPreds(CriteriaQuery cq, Root root) {
//        List<Predicate> additionalPredicates = getAdditionalPredicates(root);
//        if (additionalPredicates != null && !additionalPredicates.isEmpty()) {
//            Predicate prevRest = cq.getRestriction();
//            if (prevRest != null) {
//                additionalPredicates.add(prevRest);
//            }
//            cq.where(additionalPredicates.toArray(new Predicate[]{}));
//        }
//    }
    protected void addAdditionalPredicates(CriteriaQuery cq, List<Predicate> predicates) {
        if (predicates != null && !predicates.isEmpty()) {
            Predicate prevRest = cq.getRestriction();
            if (prevRest != null) {

            }
        }
    }

    public void flush() {
        getEntityManager().flush();
    }

    public void refresh(T t) {
        getEntityManager().refresh(t);
    }

    public List<T> findDuplicates() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(entityClass);
        cq.select(root);
        return getEntityManager().createQuery(cq).getResultList();
    }

    public T getSingleResultOrNull(CriteriaQuery cq) {
        List results = getEntityManager().createQuery(cq).getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return (T) results.get(0);
        }
        throw new NonUniqueResultException();
    }

    //@TransactionAttribute(TransactionAttributeType.MANDATORY)
    public T merge(T entity) {
        return getEntityManager().merge(entity);
    }

    //@TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    public void hardRemove(T entity) {
        try {
            getEntityManager().remove(this.getRefreshed((AbstractEntity) entity));
        } catch (Throwable e)//sve
        {
            getEntityManager().getTransaction().rollback();
        }
    }

    public List<T> getByParameter(final String colName, final String value,
            final boolean ordered) {

        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put(colName, value);

        Map<String, Object> tmpMapOrdered = new HashMap<String, Object>();

        if (ordered) {
            tmpMapOrdered = tmpMap;
        } else {
            tmpMapOrdered = null;
        }

        return getByParameters(tmpMap, tmpMapOrdered);

    }

    public List<T> getByParameters(final Map<String, Object> params,
            final Map<String, Object> orderParams) {
        final StringBuffer queryString = new StringBuffer("SELECT * FROM ");

        Table t = this.entityClass.getAnnotation(Table.class);
        String tableName = t.name();
        queryString.append(tableName).append(
                " o ");
        queryString.append(this.getQueryClauses(params, orderParams));

        final Query query = this.em.createNativeQuery(queryString.toString());

        return (List<T>) query.getResultList();
    }

    private String getQueryClauses(final Map<String, Object> params,
            final Map<String, Object> orderParams) {
        final StringBuffer queryString = new StringBuffer();
        if ((params != null) && !params.isEmpty()) {
            queryString.append(" where ");
            for (final Iterator<Map.Entry<String, Object>> it = params
                    .entrySet().iterator(); it.hasNext();) {
                final Map.Entry<String, Object> entry = it.next();
                if (entry.getValue() instanceof Boolean) {
                    queryString.append("o.").append(entry.getKey())
                            .append(" is ").append(entry.getValue())
                            .append(" ");
                } else {
                    if (entry.getValue() instanceof Number) {
                        queryString.append("o.").append(entry.getKey())
                                .append(" = ").append(entry.getValue());
                    } else {
                        // string equality
                        queryString.append("o.").append(entry.getKey())
                                .append(" = '").append(entry.getValue())
                                .append("'");
                    }
                }
                if (it.hasNext()) {
                    queryString.append(" and ");
                }
            }
        }
        if ((orderParams != null) && !orderParams.isEmpty()) {
            queryString.append(" order by ");
            for (final Iterator<Map.Entry<String, Object>> it = orderParams
                    .entrySet().iterator(); it.hasNext();) {
                final Map.Entry<String, Object> entry = it.next();
                queryString.append("o.").append(entry.getKey()).append(" ");
                if (it.hasNext()) {
                    queryString.append(", ");
                }
            }
        }
        return queryString.toString();
    }

    public static List<CStateEnum> visibleEnums() {
        return new ArrayList<>(Arrays.asList(CStateEnum.AUTO_CONFIRMED));
    }

    public AbstractEntity getRefreshed(AbstractEntity entity) {
        return getEntityManager().find(entity.getClass(), entity.getId());
    }

    public void detach (T entity){
        getEntityManager().detach(entity);
    }
    public T copy(T entity){
        CopyGroup group = new CopyGroup();
        group.setShouldResetPrimaryKey(true);
        group.setShouldResetVersion(true);
        return (T) getEntityManager().unwrap(JpaEntityManager.class).copy(entity, group);
    }
}
