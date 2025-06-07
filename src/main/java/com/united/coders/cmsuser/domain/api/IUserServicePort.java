package com.united.coders.cmsuser.domain.api;

import com.united.coders.cmsuser.domain.model.User;

import java.util.List;

public interface IUserServicePort {
    User getUser(Long id);
    List<User> getUsers();
    void createUser(User user);
    void updateUser(User user, Long id);
    void deleteUser(Long id);
}
