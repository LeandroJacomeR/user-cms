package com.united.coders.cmsuser.domain.spi;

import com.united.coders.cmsuser.domain.model.Permission;

import java.util.List;

public interface IPermissionPersistencePort {
    List<Permission> getPermissions();
}
