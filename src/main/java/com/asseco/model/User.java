package com.asseco.model;

import com.asseco.enumeration.UserTypeEnum;
import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author darko.aleksovski
 */
@Entity
@Table(name = "USER")
public class User extends AbstractEntity {

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
    }


    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "SKIN")
    private String skin;

    @Column(name = "IME")
    private String ime;

    @Column(name = "PREZIME")
    private String prezime;

    @Column(name = "TELEFON")
    private String telefon;

    @ManyToOne(optional = false, targetEntity = Company.class)
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ID")
    private Organization organization;

    @Column(name = "TYPE", length = 255)
    @Enumerated(EnumType.ORDINAL)
    private UserTypeEnum type;

    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    @ManyToMany
    @CascadeOnDelete
    private List<Role> listRoles;

    @OneToMany(targetEntity = Attachment.class, mappedBy = "userID", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> documents;


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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Role> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<Role> listRoles) {
        this.listRoles = listRoles;
    }




    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public List<Attachment> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Attachment> documents) {
        this.documents = documents;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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
}
