package com.united.coders.cmsuser.adapter.driving.http.handlers.impl;

import com.united.coders.cmsuser.adapter.driving.http.dto.request.UserRequestDto;
import com.united.coders.cmsuser.adapter.driving.http.dto.response.UserResponseDto;
import com.united.coders.cmsuser.adapter.driving.http.handlers.IUserHandler;
import com.united.coders.cmsuser.adapter.driving.http.mapper.IUserRequestMapper;
import com.united.coders.cmsuser.adapter.driving.http.mapper.IUserResponseMapper;
import com.united.coders.cmsuser.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public UserResponseDto getUser(Long id) {
        return userResponseMapper.toResponse(userServicePort.getUser(id));
    }

    @Override
    public List<UserResponseDto> getUsers() {
        return userResponseMapper.toResponseList(userServicePort.getUsers());
    }

    @Override
    public void createUser(UserRequestDto user) {
        System.out.println("name: " + user.getName());
        userServicePort.createUser(userRequestMapper.toUser(user));
    }

    @Override
    public void updateUser(UserRequestDto user) {
        userServicePort.updateUser(userRequestMapper.toUser(user));
    }

    @Override
    public void deleteUser(Long id) {
        userServicePort.deleteUser(id);
    }
}
