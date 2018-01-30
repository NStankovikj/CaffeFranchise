/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author atanasij.josifoski
 */
@Entity
@Table(name = "MESSAGE")
public class Message extends AbstractEntity {

    @Column(name = "TITLE", length = 250)
    private String title;

    @Column(name = "CONTENT", length = 500)
    private String content;

    @Column(name = "SEEN")
    private Integer seen;

    @Column(name = "SEENTIME")
    private Date seenTime;

    @Column(name = "MARK_READ")
    private Integer markRead;

    @Column(name = "MARK_IMPORTANT")
    private Integer markImportant;

    @ManyToOne
    @JoinColumn(name = "SENDER")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "RECEIVER")
    private User receiver;

    @OneToMany(targetEntity = Attachment.class, mappedBy = "message", cascade = CascadeType.REMOVE)
    private List<Attachment> attachments;

    @Override
    public String getSelectMenuLabel() {
        return title;
    }

    @Override
    public void setClassData() {
        return;
    }

    @Override
    public void genericSet(String attributeName, Object value) throws Exception {
        this.getClass().getDeclaredField(attributeName).set(this, this.genericSetTemplate(attributeName, value));
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

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

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Integer getSeen() {
        return seen;
    }

    public void setSeen(Integer seen) {
        this.seen = seen;
    }

    public Date getSeenTime() {
        return seenTime;
    }

    public void setSeenTime(Date seenTime) {
        this.seenTime = seenTime;
    }

    public Integer getMarkRead() {
        return markRead;
    }

    public void setMarkRead(Integer markRead) {
        this.markRead = markRead;
    }

    public Integer getMarkImportant() {
        return markImportant;
    }

    public void setMarkImportant(Integer markImportant) {
        this.markImportant = markImportant;
    }

}
