/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.enumeration;

/**
 *
 * @author petar.milevski
 */
public enum VidPostapkaEnum {

    NONE(0, "Непополнето"),
    NACIONALNA(1, "Национална постапка"),
    PRIZNAVANJE(2, "Постапка врз основа на признавање на одобрение за ставање во промет во ЕУ добиено според");

    int id;
    String description;

    private VidPostapkaEnum(int id, String description) {
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

    //krpenici
    public static VidPostapkaEnum getVidPostapka(Integer id)
    {
        for (VidPostapkaEnum u : values()) {
            if (u.id == id) {
                return u;
            }
        }
        return null;
    }

    public VidPostapkaEnum getById(int id) {
        return VidPostapkaEnum.getVidPostapka(id);
    }


}
