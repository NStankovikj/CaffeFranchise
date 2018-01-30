package com.asseco.model;

import org.eclipse.persistence.annotations.CacheType;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "EMPLOYEE")
public class Employee extends AbstractEntity {



    @Column(name = "IME")
    private String ime;

    @Column(name = "PREZIME")
    private String prezime;

    @Column(name = "TELEFON")
    private String telefon;

    @ManyToOne(optional = false, targetEntity = CaffeSubCompany.class)
    @JoinColumn(name = "CaffeSubCompany", referencedColumnName = "ID")
    private CaffeSubCompany caffeSubCompany;


    public CaffeSubCompany getCaffeSubCompany() {
        return caffeSubCompany;
    }

    public void setCaffeSubCompany(CaffeSubCompany caffeSubCompany) {
        this.caffeSubCompany = caffeSubCompany;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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
