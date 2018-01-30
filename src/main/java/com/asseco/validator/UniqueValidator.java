/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.validator;

import com.asseco.dao.ValidatorDAO;
import com.asseco.model.AbstractEntity;
import org.primefaces.validate.ClientValidator;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author nenad.stankovik
 */
@Named
@RequestScoped
public class UniqueValidator implements Validator, ClientValidator {

    @EJB
    ValidatorDAO validatorDao;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        Class entityClass = component.getAttributes().get("currentEntity").getClass();
        AbstractEntity ab = (AbstractEntity) component.getAttributes().get("currentEntity");
        String idTmp = "";
        if (ab != null && ab.getId() != null) {
            idTmp = ab.getId();
        }
        String uniqueColumn = (String) component.getAttributes().get("uniqueColumn");

        boolean isUnique = validatorDao.checkUnique(entityClass, uniqueColumn, value, idTmp);

        if (!isUnique) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ResourceBundle bundle = ResourceBundle.getBundle("ValidationMessages", fc.getViewRoot().getLocale());

            String msg = bundle.getString("duplicate_error");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,
                    null));
        }
    }

    @Override
    public Map<String, Object> getMetadata() {
        return null;
    }

    @Override
    public String getValidatorId() {
        return "uniqueValidator";
    }

}
