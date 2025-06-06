package com.united.coders.cmsuser.adapter.driving.http.dto.response;

public class PermissionResponseDto {
    private String action;
    private String resource;

    public PermissionResponseDto(String action, String resource) {
        this.action = action;
        this.resource = resource;
    }

    public String getAction() {
        return action;
    }

    public String getResource() {
        return resource;
    }
}
