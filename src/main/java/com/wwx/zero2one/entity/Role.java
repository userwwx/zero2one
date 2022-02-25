package com.wwx.zero2one.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 
 * 
 */
public class Role implements Serializable {
    private Integer id;

    private String roleName;

    private Integer activate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getActivate() {
        return activate;
    }

    public void setActivate(Integer activate) {
        this.activate = activate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(roleName, role.roleName) && Objects.equals(activate, role.activate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, activate);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", activate=" + activate +
                '}';
    }
}