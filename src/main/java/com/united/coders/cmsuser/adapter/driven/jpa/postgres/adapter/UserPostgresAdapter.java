package com.united.coders.cmsuser.adapter.driven.jpa.postgres.adapter;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.RoleEntity;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.UserEntity;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.NoDataFoundException;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.RoleNotAllowedForCreationException;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.UserAlreadyExistsException;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.UserNotFoundException;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.mappers.IRoleEntityMapper;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.mappers.IUserEntityMapper;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories.IRoleRepository;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories.IUserRepository;
import com.united.coders.cmsuser.domain.model.Role;
import com.united.coders.cmsuser.domain.model.User;
import com.united.coders.cmsuser.domain.spi.IUserPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static com.united.coders.cmsuser.configuration.Contants.USER;

@RequiredArgsConstructor
@Transactional
public class UserPostgresAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public List<User> getUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public void createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new UserAlreadyExistsException();
//        if (user.getRole().equals(USER))
//            throw new RoleNotAllowedForCreationException();

        if (user.getRole() == null) {
            RoleEntity defaultRole = roleRepository.findByName(USER)
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            user.setRole(roleEntityMapper.toRole(defaultRole));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public void updateUser(User user) {
        UserEntity existingUser = userRepository.findById(user.getId())
                .orElseThrow(UserNotFoundException::new);

        if (!existingUser.getEmail().equals(user.getEmail()) &&
                userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
        if (user.getName() != null) existingUser.setName(user.getName());
        if (user.getRole() != null) existingUser.setRole(roleEntityMapper.toEntity(user.getRole()));
        if (user.getPassword() != null) existingUser.setPassword(user.getPassword());

        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
