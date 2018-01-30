/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.enumeration;

/**
 *
 * @author atanasij.josifoski
 */
public enum UlogaOeEnum {

    VRABOTEN(0, "Вработен"),
    ZAMENIK_RAKOVODITEL(1, "Заменик раководител"),
    RAKOVODITEL(2, "Раководител");

    private final Integer id;
    private final String descrption;

    private UlogaOeEnum(Integer id, String descrption) {
        this.id = id;
        this.descrption = descrption;
    }

    public Integer getId() {
        return id;
    }

    public String getDescrption() {
        return descrption;
    }

    public static String getById(int id) {
        for (UlogaOeEnum u : values()) {
            if (u.id.equals(id)) {
                return u.descrption;
            }
        }
        return null;
    }
}
