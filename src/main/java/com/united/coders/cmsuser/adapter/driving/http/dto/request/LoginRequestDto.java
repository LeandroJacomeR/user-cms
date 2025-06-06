package com.united.coders.cmsuser.adapter.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
