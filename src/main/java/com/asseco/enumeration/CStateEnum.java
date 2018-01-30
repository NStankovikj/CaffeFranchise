/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.enumeration;

/**
 * Opisuva kako e kreriran daden entitet, go ima vo abstract entity
 * @author petar.milevski
 */
public enum CStateEnum {
    //null - normalno kreirano
    AUTO_CONFIRMED(0, "AUTO_CONFIRMED"),
    AUTO_UNCONFIRMED(1, "AUTO_UNCONFIRMED"),
    AUTO_DELETE(9, "AUTO_DELETE");//ne raboti brisenjeto, go oznacuvam samo kako izbrisano

    int id;
    String description;

    private CStateEnum(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static CStateEnum getAutoConfirmed() {
        return AUTO_CONFIRMED;
    }

    public static CStateEnum getAutoUnconfirmed() {
        return AUTO_UNCONFIRMED;
    }



}
