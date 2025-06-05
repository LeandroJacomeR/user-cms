package com.united.coders.cmsuser.domain.model;

public class Permission {
    private Long id;
    private String action;
    private String resource;

    public Permission(Long id, String action, String resource) {
        this.id = id;
        this.action = action;
        this.resource = resource;
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
}
