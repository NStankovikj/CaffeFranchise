package com.asseco.controller;

import com.asseco.controller.lazy.GenericLazyDataModel;
import com.asseco.model.AbstractEntity;
import org.primefaces.model.LazyDataModel;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kiril.micev
 * @param <T>
 */
public abstract class AbstractCrudControler<T extends AbstractEntity> extends AbstractBaseCrudController<T> {

    private static final long serialVersionUID = 1L;

    private LazyDataModel<T> lazyDataModel;

    private Boolean requestViewOnly;
    private Boolean requestViewDialog;

    protected LazyDataModel populateLazyDataModel() {
        return new GenericLazyDataModel<>(getDAO(), getFilter());
    }

    public LazyDataModel<T> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = populateLazyDataModel();
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<T> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    @Override
    public void refresh() {
        super.refresh();
        lazyDataModel = null;
    }


    public Boolean getRequestViewOnly() {
        return requestViewOnly;
    }

    public void setRequestViewOnly(Boolean requestViewOnly) {
        this.requestViewOnly = requestViewOnly;
    }

    public Boolean getRequestViewDialog() {
        return requestViewDialog;
    }

    public void setRequestViewDialog(Boolean requestViewDialog) {
        this.requestViewDialog = requestViewDialog;
    }

}
