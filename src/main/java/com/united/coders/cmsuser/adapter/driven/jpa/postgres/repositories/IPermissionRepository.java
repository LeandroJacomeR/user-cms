package com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
