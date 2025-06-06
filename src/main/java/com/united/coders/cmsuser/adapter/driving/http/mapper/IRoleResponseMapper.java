package com.united.coders.cmsuser.adapter.driving.http.mapper;

import com.united.coders.cmsuser.adapter.driving.http.dto.response.RoleResponseDto;
import com.united.coders.cmsuser.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleResponseMapper {
    RoleResponseDto toResponse(Role role);
    List<RoleResponseDto> toResponseList(List<Role> roles);
}
