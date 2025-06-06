package com.united.coders.cmsuser.adapter.driving.http.dto.response;

import java.util.Set;

public class RoleResponseDto {
    private String name;
    private Set<PermissionResponseDto> permissions;

    public RoleResponseDto(String name, Set<PermissionResponseDto> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public Set<PermissionResponseDto> getPermissions() {
        return permissions;
    }
}
