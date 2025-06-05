package com.united.coders.cmsuser.adapter.driven.jpa.postgres.adapter;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.RoleEntity;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.NoDataFoundException;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.mappers.IRoleEntityMapper;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories.IRoleRepository;
import com.united.coders.cmsuser.domain.model.Role;
import com.united.coders.cmsuser.domain.spi.IRolePersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
public class RolePostgresAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public List<Role> getRoles() {
        List<RoleEntity> roleEntityList = roleRepository.findAll();
        if (roleEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return roleEntityMapper.toRoleList(roleEntityList);
    }
}
