package com.united.coders.cmsuser.domain.usecase;

import com.united.coders.cmsuser.domain.api.IRoleServicePort;
import com.united.coders.cmsuser.domain.model.Role;
import com.united.coders.cmsuser.domain.spi.IRolePersistencePort;

import java.util.List;

public class RoleUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public List<Role> getRoles() {
        return rolePersistencePort.getRoles();
    }
}
