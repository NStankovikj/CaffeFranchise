package com.asseco.controller;

import com.asseco.model.AbstractEntity;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;

/**
 *
 * @author kiril.micev
 */
public abstract class AbstractBaseCrudController<T extends AbstractEntity> extends AbstractController<T> {

    private static final long serialVersionUID = 1L;

    public void createNew() {
        T newAe = prepareNew();
        setSelected(newAe);
    }

    public void update(T t) {
        getDAO().edit(t);
        getLogger().log(Level.SEVERE, "bla");
        refresh();

    }

    public void delete(T t) {
        try {
            getDAO().remove(t);
            refresh();
        } catch (Exception ex) {
            try {
                throw new SQLException(ex);
            } catch (SQLException ex1) {
                try {
                    throw new SQLIntegrityConstraintViolationException(ex1);
                } catch (SQLIntegrityConstraintViolationException ex2) {
//                    Logger.getLogger(AbstractBaseCrudController.class.getName()).log(Level.SEVERE, null, ex2);
                    addMessage("ChildDeletionError");
                }
            }
        }

    }

    public void updateSelected() {
        if (getSelected() != null) {
            update(getSelected());
        }
        refresh();
    }

    public void deleteSelected() {
        if (getSelected() != null) {
            delete(getSelected());
        }
        refresh();
    }

    public void saveSelected() {
        if (getSelected() != null) {
            getDAO().create(getSelected());
        }
        refresh();
    }

}
