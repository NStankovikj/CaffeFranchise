package com.asseco.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CAFFESUBCOMPANY")
public class CaffeSubCompany extends AbstractEntity {

    @Column(name = "NAME")
    @Basic
    private String name;

    @ManyToOne(optional = false, targetEntity = CaffeCompany.class)
    @JoinColumn(name = "CAFFE_COMPANY", referencedColumnName = "ID")
    private CaffeCompany caffeCompany;



    @OneToMany(targetEntity = Employee.class, mappedBy = "caffeSubCompany", fetch = FetchType.EAGER,  cascade =CascadeType.ALL )
    private List<Employee> employee;

    public CaffeCompany getCaffeCompany() {
        return caffeCompany;
    }

    public void setCaffeCompany(CaffeCompany caffeCompany) {
        this.caffeCompany = caffeCompany;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
