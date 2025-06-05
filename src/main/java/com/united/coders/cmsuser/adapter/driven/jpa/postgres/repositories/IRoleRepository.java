package com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
}
