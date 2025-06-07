package com.united.coders.cmsuser.adapter.driving.http.handlers;


import com.united.coders.cmsuser.adapter.driving.http.dto.request.UserRequestDto;
import com.united.coders.cmsuser.adapter.driving.http.dto.response.UserResponseDto;

import java.util.List;

public interface IUserHandler {
    UserResponseDto getUser(Long id);
    List<UserResponseDto> getUsers();
    void createUser(UserRequestDto user);
    void updateUser(UserRequestDto user, Long id);
    void deleteUser(Long id);
}
