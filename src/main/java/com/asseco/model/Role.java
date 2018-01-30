
package com.asseco.model;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.*;
import java.util.List;

/**
 * @author darko.aleksovski
 */
@Entity
@Table(name = "ROLE")
public class Role extends AbstractEntity {

    @Override
    public String getSelectMenuLabel() {
        return name;
    }

    @Override
    public void setClassData() {
        return;
    }

    @Override
    public void genericSet(String attributeName, Object value) throws Exception {
        this.getClass().getDeclaredField(attributeName).set(this, this.genericSetTemplate(attributeName, value));
        ;
    }

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "listRoles", fetch = FetchType.LAZY)
    private List<User> listUsers;

    @ManyToMany(mappedBy = "listRoles", fetch = FetchType.LAZY)
    private List<UserRequestRegistration> listUserRequestRegistration;

    @JoinTable(
            name = "ROLE_PERMISSION",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID")
    )
    @ManyToMany
    private List<Permission> listPermission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<Permission> getListPermission() {
        return listPermission;
    }

    public void setListPermission(List<Permission> listPermission) {
        this.listPermission = listPermission;
    }

    public List<UserRequestRegistration> getListUserRequestRegistration() {
        return listUserRequestRegistration;
    }

    public void setListUserRequestRegistration(List<UserRequestRegistration> listUserRequestRegistration) {
        this.listUserRequestRegistration = listUserRequestRegistration;
    }
}
