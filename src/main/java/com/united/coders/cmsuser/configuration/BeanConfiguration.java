package com.united.coders.cmsuser.configuration;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.adapter.RolePostgresAdapter;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.adapter.UserPostgresAdapter;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.mappers.IRoleEntityMapper;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.mappers.IUserEntityMapper;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories.IRoleRepository;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories.IUserRepository;
import com.united.coders.cmsuser.domain.api.IRoleServicePort;
import com.united.coders.cmsuser.domain.api.IUserServicePort;
import com.united.coders.cmsuser.domain.spi.IRolePersistencePort;
import com.united.coders.cmsuser.domain.spi.IUserPersistencePort;
import com.united.coders.cmsuser.domain.usecase.RoleUseCase;
import com.united.coders.cmsuser.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    private final PasswordEncoder passwordEncoder;

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RolePostgresAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserPostgresAdapter(userRepository, userEntityMapper, roleRepository, roleEntityMapper, passwordEncoder);
    }
}
