package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewUserRequest;
import ru.practicum.ewmserver.dto.user.UserDto;
import ru.practicum.ewmserver.dto.user.UserShortDto;
import ru.practicum.ewmserver.entity.User;

@Mapper
public interface UserMapper {

    @Named("toDto")
    UserDto toDto(User user);

    @Named("toShortDto")
    UserShortDto toShortDto(User user);

    User toEntity(NewUserRequest newUserRequest);
}
