package com.asseco.model;

import com.asseco.enumeration.CStateEnum;
import com.asseco.exception.UnknownFlagException;
import org.eclipse.persistence.annotations.UuidGenerator;
import org.primefaces.util.SecurityUtils;

import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kiril.micev
 */
@MappedSuperclass
@UuidGenerator(name = "uuid-gen")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid-gen")
    @XmlTransient
    @Column(name = "ID")
    public String id;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATECREATED")
    private Date dateCreated;

    @OrderBy("desc")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATEMODIFIED")
    private Date dateModified;

    @Column(name = "CREATEDBY")
    private String createdBy;

    //nacin na kreiranje
    @Column(name = "CSTATE", columnDefinition = "varchar(255)")
    @Enumerated(EnumType.ORDINAL)
    private CStateEnum cState;

    //za da pokazam kade vo hierarhijata se naogja objektot pr: baranje->lek->komintent->drzava
    private transient List<String> contextBreadcrumb;
    private transient Map<AbstractEntity, String> entityBreadcrumb;

    @Column(name = "MODIFIEDBY")
    private String modifiedBy;

    @Version
    @Column(name = "VERSION")
    private Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    private transient Map<String, String> classData;

    public void setid(String id) {
        this.id = id;
    }

    @PrePersist
    public void preSave() {
//        if (Utils.isBlank(getId())) {
//            this.setId(UUID.randomUUID().toString());
//        }

        if (dateCreated == null) {
            dateCreated = new Date();
        }
        dateModified = new Date();
        String usern = "N/A";
        if (FacesContext.getCurrentInstance() != null) {
            try {

                usern = SecurityUtils.remoteUser();
            } catch (Exception ex) {
                Logger.getLogger(AbstractEntity.class.getName()).log(Level.SEVERE, "Error in PrePersist", ex);
            }
        }

//        if (createdBy == null) {
//            createdBy = usern;
//        }
//        modifiedBy = usern;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    private transient Boolean forceDuplication = false;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public abstract void genericSet(String attributeName, Object value) throws Exception;

    //todo valjda ke treba vo implementacija
    public Object genericGet(String attributeName) throws Exception {
        return this.getClass().getField(attributeName).get(this);
    }

    public Object genericSetTemplate(String attributeName, Object value) throws NoSuchFieldException, UnknownFlagException {

        Class type = this.getClass().getDeclaredField(attributeName).getType();

        //ako e string moze da se napravi obid da se kastira na nesto drugo
        //za se drugo treba da moze direktni set da se pravi
        if (value instanceof String) {
            value = this.castValue(type, (String) value);
        }

        return value;
    }

    private Object castValue(Class type, String value) throws UnknownFlagException {

        if (value.trim().equals("")) {
            return null;
        }

        if (type == String.class) {
            return value;
        } else if (type == Double.class || type == double.class) {
            return Double.parseDouble(value);
        } else if (type == Float.class || type == float.class) {
            return Float.parseFloat(value);
        } else if (type == Boolean.class || type == boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (type == Long.class || type == long.class) {
            return Long.parseLong(value);
        } else if (type == Short.class || type == short.class) {
            return Short.parseShort(value);
        } else if (type == Character.class || type == char.class) {
            return value.charAt(0);
        } else if (type == Integer.class || type == int.class) {
            return Integer.parseInt(value);
        } else if (type == Date.class) {
            return Date.parse(value);
        } else if (type.isEnum()) {
            return type.getEnumConstants()[Integer.parseInt(value)];
        } else {
            throw new UnknownFlagException("AbstractEntity: Unknown type to cast to: " + type.getSimpleName());
        }

    }

    abstract public String getSelectMenuLabel();

    abstract public void setClassData();

    public CStateEnum getcState() {
        return cState;
    }

    public void setcState(CStateEnum cState) {
        this.cState = cState;
    }

    public List<String> getContextBreadcrumb() {
        if (contextBreadcrumb == null) {
            contextBreadcrumb = new ArrayList<>();
        }
        return contextBreadcrumb;
    }

    public Map<AbstractEntity, String> getEntityBreadcrumb() {
        if (entityBreadcrumb == null) {
            entityBreadcrumb = new LinkedHashMap<>();
        }
        return entityBreadcrumb;
    }

    public void setEntityBreadcrumb(Map<AbstractEntity, String> entityBreadcrumb) {
        this.entityBreadcrumb = entityBreadcrumb;
    }

    public void setContextBreadcrumb(List<String> contextBreadcrumb) {
        this.contextBreadcrumb = contextBreadcrumb;
    }

    public Map<String, String> getClassData() {
        if (classData == null) {
            setClassData();
        }

        return classData;
    }

    protected String getLocalisedMessage(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        }
        return "???" + key + "???";
    }

    public Boolean getForceDuplication() {
        return forceDuplication;
    }

    public void setForceDuplication(Boolean forceDuplication) {
        this.forceDuplication = forceDuplication;
    }

    public void setClassData(Map<String, String> classData) {
        this.classData = classData;
    }
}
