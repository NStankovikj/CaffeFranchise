/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.model;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author darko.aleksovski
 */
@Entity
@Table(name = "PERMISSION")
public class Permission extends AbstractEntity {
    
    @Override
    public void genericSet(String attributeName, Object value) throws Exception {
        this.getClass().getDeclaredField(attributeName).set(this,this.genericSetTemplate(attributeName,value));;
    }

    @Override
    public String getSelectMenuLabel() {
        return name;
    }

    @Override
    public void setClassData() {
        return;
    }

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "PAGE")
    private String page;
    
    @Column(name = "TITLE")
    private String title;
    
    @ManyToMany(mappedBy = "listPermission", fetch = FetchType.LAZY)
    private List<Role> listRole;   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Role> getListRole() {
        return listRole;
    }

    public void setListRole(List<Role> listRole) {
        this.listRole = listRole;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
   
}
