package com.united.coders.cmsuser.adapter.driven.jpa.postgres.mappers;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.UserEntity;
import com.united.coders.cmsuser.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {
    UserEntity toEntity(User user);
    User toUser(UserEntity userEntity);
    List<User> toUserList(List<UserEntity> userEntityList);
}
