package com.united.coders.cmsuser.adapter.driving.http.handlers;

import com.united.coders.cmsuser.adapter.driving.http.dto.request.LoginRequestDto;
import com.united.coders.cmsuser.adapter.driving.http.dto.request.UserRequestDto;
import com.united.coders.cmsuser.adapter.driving.http.dto.response.JwtResponseDto;
import com.united.coders.cmsuser.adapter.driving.http.dto.response.RegisterResponseDto;

import java.text.ParseException;

public interface IAuthHandler {
    JwtResponseDto login(LoginRequestDto loginRequestDto);
    RegisterResponseDto register(UserRequestDto userRequestDto);
    JwtResponseDto refresh(JwtResponseDto jwtResponseDto) throws ParseException;
}
