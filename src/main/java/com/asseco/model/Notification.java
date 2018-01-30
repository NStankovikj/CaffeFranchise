/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.model;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author atanasij.josifoski
 */
@Entity
@Table(name = "NOTIFICATIONS")
public class Notification extends AbstractEntity {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "MAIL")
    private Integer toSendMail;

    @Column(name = "MAIL_ADDRESS")
    private String mailAddress;

    @Column(name = "DATE_SCHEDULED")
    private Date dateScheduled;

    @Column(name = "HOURS")
    private Integer hours;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "RECEIVER")
    private Lice receiver;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getToSendMail() {
        return toSendMail;
    }

    public void setToSendMail(Integer toSendMail) {
        this.toSendMail = toSendMail;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public Date getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(Date dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Lice getReceiver() {
        return receiver;
    }

    public void setReceiver(Lice receiver) {
        this.receiver = receiver;
    }

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

}
