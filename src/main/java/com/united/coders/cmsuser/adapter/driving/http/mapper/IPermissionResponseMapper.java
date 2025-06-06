package com.united.coders.cmsuser.adapter.driving.http.mapper;

import com.united.coders.cmsuser.adapter.driving.http.dto.response.PermissionResponseDto;
import com.united.coders.cmsuser.domain.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPermissionResponseMapper {
    PermissionResponseDto toResponse(Permission permission);
}
