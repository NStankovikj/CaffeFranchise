/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.converter;

import com.asseco.model.User;

import javax.faces.convert.FacesConverter;

/**
 *
 * @author atanasij.josifoski
 */
@FacesConverter(forClass = User.class)
public class UserConverter extends AbstractELConverter {

    @Override
    protected String getElName() {
        return "userController";
    }

}
