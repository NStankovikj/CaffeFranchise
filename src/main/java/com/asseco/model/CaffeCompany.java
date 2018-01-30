package com.asseco.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CAFFECOMPANY")
public class CaffeCompany extends AbstractEntity{

    @Column(name = "NAME")
    @Basic
    private String name;

    @OneToMany(targetEntity = CaffeSubCompany.class, mappedBy = "caffeCompany", fetch = FetchType.EAGER)
    private List<CaffeSubCompany> CaffeSubCompany;

    public void setName(String name) {
        this.name = name;
    }

    public List<com.asseco.model.CaffeSubCompany> getCaffeSubCompany() {
        return CaffeSubCompany;
    }

    public void setCaffeSubCompany(List<com.asseco.model.CaffeSubCompany> caffeSubCompany) {
        CaffeSubCompany = caffeSubCompany;
    }

    public String getName() {
        return name;
    }


    @Override
    public void genericSet(String attributeName, Object value) throws Exception {

    }

    @Override
    public String getSelectMenuLabel() {
        return null;
    }

    @Override
    public void setClassData() {

    }
}
