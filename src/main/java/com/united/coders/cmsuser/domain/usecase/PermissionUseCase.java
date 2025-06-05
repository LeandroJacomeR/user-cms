package com.united.coders.cmsuser.domain.usecase;

import com.united.coders.cmsuser.domain.api.IPerimssionServicePort;
import com.united.coders.cmsuser.domain.model.Permission;
import com.united.coders.cmsuser.domain.spi.IPermissionPersistencePort;

import java.util.List;

public class PermissionUseCase implements IPerimssionServicePort {
    private final IPermissionPersistencePort permissionPersistencePort;

    public PermissionUseCase(IPermissionPersistencePort permissionPersistencePort) {
        this.permissionPersistencePort = permissionPersistencePort;
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionPersistencePort.getPermissions();
    }
}
