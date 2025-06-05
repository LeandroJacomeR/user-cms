package com.united.coders.cmsuser.domain.spi;

import com.united.coders.cmsuser.domain.model.Role;

import java.util.List;

public interface IRolePersistencePort {
    List<Role> getRoles();
}
