package com.united.coders.cmsuser.adapter.driven.jpa.postgres.mappers;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.PermissionEntity;
import com.united.coders.cmsuser.domain.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPermissionEntityMapper {
    PermissionEntity toEntity(Permission permission);
    Permission toPermission(PermissionEntity permissionEntity);
    List<Permission> toPermissionList(List<PermissionEntity> permissionEntities);
}
