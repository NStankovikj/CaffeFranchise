/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.util.jsf;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.io.Serializable;

/**
 *
 * @author simon
 */
@Named
@Dependent
public class MonthYearInputC implements Serializable {

    private static final long serialVersionUID = 1L;

    private int[] months = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private int[] years = new int[]{2014, 2015, 2016, 2017};

    public int[] getMonths() {
        return months;
    }

    public void setMonths(int[] months) {
        this.months = months;
    }

    public int[] getYears() {
        return years;
    }

    public void setYears(int[] years) {
        this.years = years;
    }

}
