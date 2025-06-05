package com.united.coders.cmsuser.adapter.driven.jpa.postgres.mappers;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.RoleEntity;
import com.united.coders.cmsuser.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleEntityMapper {
    RoleEntity toEntity(Role role);
    Role toRole(RoleEntity roleEntity);
    List<Role> toRoleList(List<RoleEntity> roleEntities);
}
