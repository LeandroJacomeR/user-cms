package com.united.coders.cmsuser.adapter.driven.jpa.postgres.adapter;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.PrincipalUser;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.RoleEntity;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.UserEntity;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.united.coders.cmsuser.configuration.Contants.USER_NOT_FOUND_MESSAGE;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));

        List<RoleEntity> roles = List.of(user.getRole());

        return PrincipalUser.build(user, roles);
    }
}
