/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.dao;

import com.asseco.model.Attachment;

import javax.ejb.Stateless;

/**
 *
 * @author atanasij.josifoski
 */
@Stateless
public class AttachmentDAO extends AbstractDAO<Attachment> {

    public AttachmentDAO() {
        super(Attachment.class);
    }

}
