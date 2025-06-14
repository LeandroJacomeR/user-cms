package com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
