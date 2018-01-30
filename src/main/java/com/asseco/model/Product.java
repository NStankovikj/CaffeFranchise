package com.asseco.model;


import javax.persistence.*;


@Entity
@Table(name = "PRODUCT")
public class Product extends AbstractEntity{

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public void genericSet(String attributeName, Object value) throws Exception {

    }

    @Override
    public String getSelectMenuLabel() {
        return null;
    }

    @Override
    public void setClassData() {

    }
}
