package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewmserver.dto.user.UserDto;
import ru.practicum.ewmserver.dto.user.UserShortDto;
import ru.practicum.ewmserver.entity.User;

@Mapper
public interface UserMapper {

    UserDto toDto(User user);
    UserShortDto toShortDto(User user);

    User dtoToUser (UserDto userDto);
    User shortDtoToUser(UserShortDto userShortDto);
}
