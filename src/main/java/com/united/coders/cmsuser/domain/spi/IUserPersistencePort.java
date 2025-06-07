package com.united.coders.cmsuser.domain.spi;

import com.united.coders.cmsuser.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {
    User getUser(Long id);
    List<User> getUsers();
    void createUser(User user);
    void updateUser(User user, Long id);
    void deleteUser(Long id);
}
