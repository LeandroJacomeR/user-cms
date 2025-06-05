package com.united.coders.cmsuser.domain.usecase;

import com.united.coders.cmsuser.domain.api.IUserServicePort;
import com.united.coders.cmsuser.domain.model.User;
import com.united.coders.cmsuser.domain.spi.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public User getUser(Long id) {
        return userPersistencePort.getUser(id);
    }

    @Override
    public List<User> getUsers() {
        return userPersistencePort.getUsers();
    }

    @Override
    public void createUser(User user) {
        userPersistencePort.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        userPersistencePort.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userPersistencePort.deleteUser(id);
    }
}
