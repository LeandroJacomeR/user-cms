package com.united.coders.cmsuser.adapter.driving.http.handlers.impl;

import com.united.coders.cmsuser.adapter.driving.http.dto.response.RoleResponseDto;
import com.united.coders.cmsuser.adapter.driving.http.handlers.IRoleHandler;
import com.united.coders.cmsuser.adapter.driving.http.mapper.IRoleResponseMapper;
import com.united.coders.cmsuser.domain.api.IRoleServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleHandlerImpl implements IRoleHandler {

    private final IRoleServicePort roleServicePort;
    private final IRoleResponseMapper roleResponseMapper;


    @Override
    public List<RoleResponseDto> getRoles() {
        return roleResponseMapper.toResponseList(roleServicePort.getRoles());
    }
}
