package com.united.coders.cmsuser.adapter.driving.http.mapper;

import com.united.coders.cmsuser.adapter.driving.http.dto.request.UserRequestDto;
import com.united.coders.cmsuser.adapter.driving.http.dto.response.UserResponseDto;
import com.united.coders.cmsuser.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    User toUser(UserRequestDto dto);
    UserResponseDto toUserResponseDto(UserRequestDto user);
}
