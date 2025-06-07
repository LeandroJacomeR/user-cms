package com.united.coders.cmsuser.domain.model;

import com.united.coders.cmsuser.domain.exception.FormatEmailException;
import com.united.coders.cmsuser.domain.exception.FormatPasswordException;

import java.util.regex.Pattern;

import static com.united.coders.cmsuser.domain.util.DomainConstants.REGEX_EMAIL;
import static com.united.coders.cmsuser.domain.util.DomainConstants.REGEX_PASSWORD;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;

    public User(Long id, String name, String email, String password, Role role) {
        if (email != null && !Pattern.matches(REGEX_EMAIL, email)) throw new FormatEmailException();
        if (password != null && !Pattern.matches(REGEX_PASSWORD, password)) throw new FormatPasswordException();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
