package com.united.coders.cmsuser.adapter.driven.jpa.postgres.adapter;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.UserEntity;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.NoDataFoundException;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.UserAlreadyExistsException;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.UserNotFoundException;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.mappers.IRoleEntityMapper;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.mappers.IUserEntityMapper;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories.IRoleRepository;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.repositories.IUserRepository;
import com.united.coders.cmsuser.domain.model.User;
import com.united.coders.cmsuser.domain.spi.IUserPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Transactional
public class UserPostgresAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

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
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        //TODO: Terminar el resto de metodos
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
