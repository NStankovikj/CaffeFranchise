/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.util.jsf;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author simon
 */
@FacesComponent("inputMonthYear")
public class inputMonthYear extends UIComponentBase {

    private UIInput month;
    private UIInput year;

    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }

    public UIInput getMonth() {
        return month;
    }

    public void setMonth(UIInput month) {
        this.month = month;
    }

    public UIInput getYear() {
        return year;
    }

    public void setYear(UIInput year) {
        this.year = year;
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttributeValue(String key, T defaultValue) {
        T value = (T) getAttributes().get(key);
        return (value != null) ? value : defaultValue;
    }

    private static Integer[] createIntegerArray(int begin, int end) {
        int direction = (begin < end) ? 1 : (begin > end) ? -1 : 0;
        int size = Math.abs(end - begin) + 1;
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = begin + (i * direction);
        }

        return array;
    }

    public Integer[] getMonths() {
        return (Integer[]) getStateHelper().get("months");
    }

    public void setMonths(Integer[] months) {
        getStateHelper().put("months", months);
    }

    public Integer[] getYears() {
        return (Integer[]) getStateHelper().get("years");
    }

    public void setYears(Integer[] years) {
        getStateHelper().put("years", years);
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        Calendar calendar = Calendar.getInstance();
        int maxYear = getAttributeValue("maxyear", calendar.get(Calendar.YEAR) + 2);
        int minYear = getAttributeValue("minyear", maxYear - 3);

        UIInput _year = getAttributeValue("year", null);
        UIInput _month = getAttributeValue("month", null);
//
        month.setValue(_month.getValue());
        year.setValue(_year.getValue());
        if (year.getValue() == null) {
            year.setValue(calendar.get(Calendar.YEAR));
        }
        if (month.getValue() == null) {
            month.setValue(calendar.get(Calendar.MONTH) + 1);
        }
        setMonths(createIntegerArray(1, calendar.getActualMaximum(Calendar.MONTH) + 1));
        setYears(createIntegerArray(maxYear, minYear));
        super.encodeBegin(context);
    }
    
    

}
