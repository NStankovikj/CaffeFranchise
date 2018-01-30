/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.themebeans;

/**
 *
 * @author atanasij.josifoski
 */

import com.github.adminfaces.template.util.Assert;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ApplicationScoped
public class SkinMBlocal
        implements Serializable {

    private String skin;
    @Inject
    private AdminConfigLocal adminConfig;

    public SkinMBlocal() {
    }

    @PostConstruct
    public void init() {
        skin = adminConfig.getSkin();
        if (!Assert.has(skin)) {
            skin = "skin-red";
        }
    }

    public void changeSkin(String skin) {
        this.skin = skin;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
}
