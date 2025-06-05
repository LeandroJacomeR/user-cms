package com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
