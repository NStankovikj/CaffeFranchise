/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.enumeration;

/**
 *
 * @author darko.aleksovski
 */
public enum VidLiceEnum {

    INTERNAL_PERSON(0, "Internal person"),
    EXTERNAL_PERSON(1, "External person");

    private final Integer id;
    private final String descrption;

    private VidLiceEnum(Integer id, String descrption) {
        this.id = id;
        this.descrption = descrption;
    }

    public Integer getId() {
        return id;
    }

    public String getDescrption() {
        return descrption;
    }

}
