package com.united.coders.cmsuser.adapter.driving.http.dto.response;

public class UserResponseDto {
    private String name;
    private String email;

    public UserResponseDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
