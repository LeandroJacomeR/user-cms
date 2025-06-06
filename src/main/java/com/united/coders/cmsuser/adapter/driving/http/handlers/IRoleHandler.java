package com.united.coders.cmsuser.adapter.driving.http.handlers;

import com.united.coders.cmsuser.adapter.driving.http.dto.response.RoleResponseDto;

import java.util.List;

public interface IRoleHandler {
    List<RoleResponseDto> getRoles();
}
