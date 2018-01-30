package com.asseco.lookups;

import com.asseco.model.AbstractEntity;
import com.asseco.model.Organization;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "Cb_Organization_Type")
@XmlRootElement
public class CbOrganizationType extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Size(max = 255)
    @Column(name = "CODE")
    private String code;
    @Size(max = 255)
    @Column(name = "LABEL")
    private String label;

    @OneToMany(targetEntity = Organization.class, mappedBy = "type", fetch = FetchType.LAZY)
    private List<Organization> Organizationcollection;

    public CbOrganizationType() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Organization> getOrganizationcollection() {
        return Organizationcollection;
    }

    public void setOrganizationcollection(List<Organization> organizationcollection) {
        Organizationcollection = organizationcollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CbOrganizationType)) {
            return false;
        }
        CbOrganizationType other = (CbOrganizationType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asseco.lookups.CbOrganizationType[ id=" + id + " ]";
    }

    @Override
    public void genericSet(String attributeName, Object value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSelectMenuLabel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setClassData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
