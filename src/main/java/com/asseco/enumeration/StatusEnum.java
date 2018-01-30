/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.enumeration;

/**
 *
 * @author igor.stojanoski
 */
public enum StatusEnum {

    DECLINED(0, "ОДБИЕН ПРЕДМЕТ"),
    ACCEPTED(1, "ПРИФАТЕН ПРЕДМЕТ");

    int id;
    String description;

    private StatusEnum(int id, String description) {
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

}
