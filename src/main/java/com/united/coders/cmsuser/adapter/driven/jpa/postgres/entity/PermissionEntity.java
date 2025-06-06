package com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "permission", schema = "public")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private String resource;

    @ManyToMany(mappedBy = "permissions")
    private Set<RoleEntity> roles;

    public PermissionEntity() {
    }

    public PermissionEntity(Long id, String action, String resource, Set<RoleEntity> roles) {
        this.id = id;
        this.action = action;
        this.resource = resource;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
