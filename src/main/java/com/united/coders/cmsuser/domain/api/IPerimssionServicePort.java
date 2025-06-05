package com.united.coders.cmsuser.domain.api;

import com.united.coders.cmsuser.domain.model.Permission;

import java.util.List;

public interface IPerimssionServicePort {
    List<Permission> getPermissions();
}
