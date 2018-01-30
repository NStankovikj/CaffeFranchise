/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author darko.aleksovski
 */
public enum UserTypeEnum {

    DOCTOR(0, "DOCTOR", VidLiceEnum.EXTERNAL_PERSON),
    ADMIN_COMPANY(1, "ADMIN IN COMPANY", VidLiceEnum.EXTERNAL_PERSON),
    VRABOTEN_MALMED(2, "EMPLOYED IN MALMED", VidLiceEnum.INTERNAL_PERSON),
    SUPER_ADMIN(3, "SUPER ADMIN", VidLiceEnum.INTERNAL_PERSON),
    RAKOVODITEL_NA_ODELENIE(4, "РАКОВОДИТЕЛ НА ОДЕЛЕНИЕ", VidLiceEnum.INTERNAL_PERSON);

    private final Integer id;
    private final String description;
    private final VidLiceEnum vidLiceEnum;

    private UserTypeEnum(Integer id, String description, VidLiceEnum vidLiceEnum) {
        this.description = description;
        this.id = id;
        this.vidLiceEnum = vidLiceEnum;
    }

    public static List<UserTypeEnum> loadByVidLice(VidLiceEnum vidLice) {
        List<UserTypeEnum> list = new ArrayList<UserTypeEnum>();
        for (UserTypeEnum u : values()) {
            if (u.vidLiceEnum.equals(vidLice)) {
                list.add(u);
            }
        }
        return list;
    }

    public static UserTypeEnum getById(Integer id) {
        for (UserTypeEnum u : values()) {
            if (u.id == id) {
                return u;
            }
        }
        return null;
    }

    public static UserTypeEnum getDOCTOR() {
        return DOCTOR;
    }

    public static UserTypeEnum getADMIN_COMPANY() {
        return ADMIN_COMPANY;
    }

    public static UserTypeEnum getVRABOTEN_MALMED() {
        return VRABOTEN_MALMED;
    }

    public static UserTypeEnum getSUPER_ADMIN() {
        return SUPER_ADMIN;
    }

    public static UserTypeEnum getRAKOVODITEL_NA_ODELENIE() {
        return RAKOVODITEL_NA_ODELENIE;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public VidLiceEnum getVidLiceEnum() {
        return vidLiceEnum;
    }

}
