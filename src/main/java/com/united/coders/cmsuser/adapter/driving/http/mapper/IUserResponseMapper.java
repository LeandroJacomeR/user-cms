package com.united.coders.cmsuser.adapter.driving.http.mapper;

import com.united.coders.cmsuser.adapter.driving.http.dto.response.UserResponseDto;
import com.united.coders.cmsuser.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
    UserResponseDto toResponse(User user);
    List<UserResponseDto> toResponseList(List<User> userList);
}
