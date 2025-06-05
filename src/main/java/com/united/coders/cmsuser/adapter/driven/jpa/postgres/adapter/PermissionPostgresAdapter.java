package com.united.coders.cmsuser.adapter.driven.jpa.postgres.adapter;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.PermissionEntity;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.NoDataFoundException;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.mappers.IPermissionEntityMapper;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories.IPermissionRepository;
import com.united.coders.cmsuser.domain.model.Permission;
import com.united.coders.cmsuser.domain.spi.IPermissionPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PermissionPostgresAdapter implements IPermissionPersistencePort {
    private final IPermissionRepository permissionRepository;
    private final IPermissionEntityMapper permissionEntityMapper;

    @Override
    public List<Permission> getPermissions() {
        List<PermissionEntity> permissionEntityList = permissionRepository.findAll();
        if (permissionEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return permissionEntityMapper.toPermissionList(permissionEntityList);
    }
}
