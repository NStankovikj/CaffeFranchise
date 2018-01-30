/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller.lazy;

import com.asseco.dao.AbstractDAO;
import com.asseco.model.AbstractEntity;


import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kiril.micev
 * @param <T>
 */
public class GenericLazyDataModel<T extends AbstractEntity> extends LazyDataModel<T> {

    private static final long serialVersionUID = 1L;
    private final AbstractDAO<T> facade;

    private Map<String, Object> filter;

    public GenericLazyDataModel(AbstractDAO<T> facade, Map<String, Object> filter) {
        this.facade = facade;
        this.filter=filter;
        setRowCount(facade.count(filter));
    }

    @Override
    public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        if (filter != null) {
            filters.putAll(filter);
        }
        List<T> r = facade.findRange(new int[]{first, pageSize + first}, filters, null, null);
        setRowCount(facade.count(filters));
        return r;
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        if (filter != null) {
            filters.putAll(filter);
        }
        List<T> r = facade.findRange(new int[]{first, pageSize + first}, filters, sortField, sortOrder);
        setRowCount(facade.count(filters));
        return r;
    }

    @Override
    public Object getRowKey(T object) {
        return object.getId();
    }

    @Override
    public T getRowData(String rowKey) {
        return facade.find(rowKey);
    }

}
