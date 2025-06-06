package com.united.coders.cmsuser.adapter.driving.http.dto.response;

public class RegisterResponseDto {
    private UserResponseDto user;
    private String token;

    public RegisterResponseDto(UserResponseDto user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
