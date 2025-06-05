package com.united.coders.cmsuser.domain.api;

import com.united.coders.cmsuser.domain.model.Role;

import java.util.List;

public interface IRoleServicePort {
    List<Role> getRoles();
}
