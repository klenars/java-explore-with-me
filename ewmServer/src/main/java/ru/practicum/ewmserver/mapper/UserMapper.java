package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewUserRequest;
import ru.practicum.ewmserver.dto.user.UserDto;
import ru.practicum.ewmserver.dto.user.UserShortDto;
import ru.practicum.ewmserver.entity.User;

/**
 * Маппер пользователей
 */
@Mapper
public interface UserMapper {

    /**
     * Маппер User в UserDto
     * @param user {@link User}
     * @return {@link UserDto}
     */
    @Named("toDto")
    UserDto toDto(User user);

    /**
     * Маппер User в UserShortDto
     * @param user {@link User}
     * @return {@link UserShortDto}
     */
    @Named("toShortDto")
    UserShortDto toShortDto(User user);

    /**
     * Маппер NewUserRequest в User
     * @param newUserRequest {@link NewUserRequest}
     * @return {@link User}
     */
    User toEntity(NewUserRequest newUserRequest);
}
