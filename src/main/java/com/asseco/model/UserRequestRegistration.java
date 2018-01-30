/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.model;

import com.asseco.lookups.CbCountry;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author darko.aleksovski
 */
@Entity
@Table(name = "USER_REQUEST_REGISTRATION")
public class UserRequestRegistration extends AbstractEntity {

    @Override
    public String getSelectMenuLabel() {
        return username;
    }

    @Override
    public void setClassData() {
        return;
    }

    @Override
    public void genericSet(String attributeName, Object value) throws Exception {
        this.getClass().getDeclaredField(attributeName).set(this, this.genericSetTemplate(attributeName, value));
        ;
    }

    @Column(name = "IME")
    private String ime;

    @Column(name = "PREZIME")
    private String prezime;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFON")
    private String telefon;

    @Column(name = "NAZIV_KOMPANIJA")
    private String nazivKompanija;

    @Column(name = "MATICEN_BROJ_KOMPANIJA")
    private String maticenBrojKompanija;

    @Column(name = "EDB_KOMPANIJA")
    private String edbKompanija;

    @Column(name = "ADRESA_SEDISTE_KOMPANIJA")
    private String adresaSedisteKompanija;

    @ManyToOne()
    @JoinColumn(name = "GRAD_KOMPANIJA")
    private Grad gradKompanija;

    @ManyToOne()
    @JoinColumn(name = "DRZAVA_KOMPANIJA")
    private CbCountry countryCompany;

    @Column(name = "VERIFY_EMEIL")
    private Boolean verify_email;

    @Column(name = "VERIFY_CODE")
    private String verify_code;

    @ManyToOne(optional = false, targetEntity = Organization.class)
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ID")
    private Organization organization;

    @ManyToMany
    @JoinTable(
            name = "USER_REQUEST_REGISTRATION_ROLE",
            joinColumns = @JoinColumn(name = "USER_REQUEST_REGISTRATION_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    )
    private List<Role> listRoles;

    @OneToMany(targetEntity = Attachment.class, mappedBy = "userRequestRegistrationID", cascade = CascadeType.ALL)
    private List<Attachment> documents;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNazivKompanija() {
        return nazivKompanija;
    }

    public void setNazivKompanija(String nazivKompanija) {
        this.nazivKompanija = nazivKompanija;
    }

    public String getMaticenBrojKompanija() {
        return maticenBrojKompanija;
    }

    public void setMaticenBrojKompanija(String maticenBrojKompanija) {
        this.maticenBrojKompanija = maticenBrojKompanija;
    }

    public String getEdbKompanija() {
        return edbKompanija;
    }

    public void setEdbKompanija(String edbKompanija) {
        this.edbKompanija = edbKompanija;
    }

    public String getAdresaSedisteKompanija() {
        return adresaSedisteKompanija;
    }

    public void setAdresaSedisteKompanija(String adresaSedisteKompanija) {
        this.adresaSedisteKompanija = adresaSedisteKompanija;
    }

    public Grad getGradKompanija() {
        return gradKompanija;
    }

    public void setGradKompanija(Grad gradKompanija) {
        this.gradKompanija = gradKompanija;
    }

    public CbCountry getCountryCompany() {
        return countryCompany;
    }

    public void setCountryCompany(CbCountry countryCompany) {
        this.countryCompany = countryCompany;
    }

    public Boolean getVerify_email() {
        return verify_email;
    }

    public void setVerify_email(Boolean verify_email) {
        this.verify_email = verify_email;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Role> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<Role> listRoles) {
        this.listRoles = listRoles;
    }

    public List<Attachment> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Attachment> documents) {
        this.documents = documents;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.ime);
        hash = 61 * hash + Objects.hashCode(this.prezime);
        hash = 61 * hash + Objects.hashCode(this.username);
        hash = 61 * hash + Objects.hashCode(this.password);
        hash = 61 * hash + Objects.hashCode(this.email);
        hash = 61 * hash + Objects.hashCode(this.telefon);
        hash = 61 * hash + Objects.hashCode(this.nazivKompanija);
        hash = 61 * hash + Objects.hashCode(this.maticenBrojKompanija);
        hash = 61 * hash + Objects.hashCode(this.edbKompanija);
        hash = 61 * hash + Objects.hashCode(this.adresaSedisteKompanija);
        hash = 61 * hash + Objects.hashCode(this.gradKompanija);
        hash = 61 * hash + Objects.hashCode(this.countryCompany);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserRequestRegistration other = (UserRequestRegistration) obj;
        return true;
    }


}
