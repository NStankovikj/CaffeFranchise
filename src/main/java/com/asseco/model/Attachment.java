/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 *
 * @author atanasij.josifoski
 */
@Entity
@Table(name = "ATTACHMENTS")
@Getter @Setter


public class Attachment extends AbstractEntity {

    @Override
    public String getSelectMenuLabel() {
        return name;
    }

    @Override
    public void setClassData() {
        return;
    }

    @Override
    public void genericSet(String attributeName, Object value) throws Exception {
        this.getClass().getDeclaredField(attributeName).set(this, this.genericSetTemplate(attributeName, value));
    }

    @Column(name = "NAME")
    private String name;

    @Column(name = "PATH")
    private String path;

    @ManyToOne
    @JoinColumn(name = "MESSAGE_ID")
    private Message message;

    @ManyToOne(targetEntity = UserRequestRegistration.class)
    @JoinColumn(name = "USER_REQUEST_REGISTRATION_ID", referencedColumnName = "ID")
    private UserRequestRegistration userRequestRegistrationID;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User userID;

}
