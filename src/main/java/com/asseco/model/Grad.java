/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.model;

import com.asseco.lookups.CbCountry;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author atanasij.josifoski
 */
@Entity
@Table(name = "GRADOVI")
public class Grad extends AbstractEntity {

    @Override
    public String getSelectMenuLabel() {
        return naziv;
    }

    @Override
    public void setClassData() {
        Map<String,String> dataset = new TreeMap<>();

        dataset.put(this.getLocalisedMessage("stavka"),this.getSelectMenuLabel());
        if(country!=null)
            dataset.put(this.getLocalisedMessage(country.getClass().getSimpleName()),this.country.getSelectMenuLabel());

        this.setClassData(dataset);
    }

    @Override
    public void genericSet(String attributeName, Object value) throws Exception {
        this.getClass().getDeclaredField(attributeName).set(this, this.genericSetTemplate(attributeName, value));;
    }

    @Column(name = "NAZIV")
    @NotNull
    private String naziv;

    @ManyToOne(optional = false, targetEntity = CbCountry.class)
    @JoinColumn(name = "DRZAVI_ID", referencedColumnName = "ID")
    private CbCountry country;

    @OneToMany(targetEntity = Organization.class, mappedBy = "city", fetch = FetchType.LAZY)
    private List<Organization> Organizationcollection;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public CbCountry getCountry() {
        return country;
    }

    public void setCountry(CbCountry country) {
        this.country = country;
    }

    public List<Organization> getOrganizationcollection() {
        return Organizationcollection;
    }

    public void setOrganizationcollection(List<Organization> organizationcollection) {
        Organizationcollection = organizationcollection;
    }
}
