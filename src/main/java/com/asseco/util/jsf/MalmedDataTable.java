/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.util.jsf;

import org.primefaces.component.api.UIData;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author kiril.micev
 */
public class MalmedDataTable extends DataTable {

    @Override
    public java.lang.String getPaginatorPosition() {
        return (java.lang.String) getStateHelper().eval(UIData.PropertyKeys.paginatorPosition, "bottom");
    }

    @Override
    public boolean isPaginator() {
        return (java.lang.Boolean) getStateHelper().eval(UIData.PropertyKeys.paginator, true);
    }

    @Override
    public int getRows() {
        if (isPaginator()) {
            if (super.getRows() == 0) {
                return 10;
            } else {
                return super.getRows();
            }
        } else {
            return getRowCount();
        }
    }

}
