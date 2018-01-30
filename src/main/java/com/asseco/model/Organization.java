package com.asseco.model;

import com.asseco.lookups.CbCountry;
import com.asseco.lookups.CbOrganizationType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORGANIZATION")
public class Organization  extends AbstractEntity{

    @Column(name = "NAME", table="ORGANIZATION", length = 60)
    @Basic
    private String name;

    @Column(name = "EDB", table="ORGANIZATION", length = 13)
    @Basic
    private String edb;

    @Column(name = "EMBS",table="ORGANIZATION", length = 7)
    @Basic
    private String embs;

    @Column(name = "IDENTIFIER", table="ORGANIZATION", length = 60)
    @Basic
    private String identifier;

    @ManyToOne(optional = false, targetEntity = CbOrganizationType.class)
    @JoinColumn(name = "type", referencedColumnName = "ID")
    private CbOrganizationType type;

    @ManyToOne(optional = false, targetEntity = CbCountry.class)
    @JoinColumn(name = "country", referencedColumnName = "ID")
    private CbCountry country;

    @ManyToOne(optional = false, targetEntity = Grad.class)
    @JoinColumn(name = "city", referencedColumnName = "ID")
    private Grad city;

    @Column(name = "ADDRESS", table="ORGANIZATION", length = 100)
    private String address;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<UserRequestRegistration> usersRequestRegistration;

    @Override
    public void genericSet(String attributeName, Object value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public String getSelectMenuLabel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void setClassData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CbOrganizationType getType() {
        return type;
    }

    public void setType(CbOrganizationType type) {
        this.type = type;
    }

    public CbCountry getCountry() {
        return country;
    }

    public void setCountry(CbCountry country) {
        this.country = country;
    }

    public Grad getCity() {
        return city;
    }

    public void setCity(Grad city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEdb() {
        return edb;
    }

    public void setEdb(String edb) {
        this.edb = edb;
    }

    public String getEmbs() {
        return embs;
    }

    public void setEmbs(String embs) {
        this.embs = embs;
    }

    public List<UserRequestRegistration> getUsersRequestRegistration() {
        return usersRequestRegistration;
    }

    public void setUsersRequestRegistration(List<UserRequestRegistration> usersRequestRegistration) {
        this.usersRequestRegistration = usersRequestRegistration;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
