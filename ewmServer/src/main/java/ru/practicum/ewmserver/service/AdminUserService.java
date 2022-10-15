package ru.practicum.ewmserver.service;

import ru.practicum.ewmserver.dto.admin.NewUserRequest;
import ru.practicum.ewmserver.dto.user.UserDto;

import java.util.List;

public interface AdminUserService {
    UserDto createUser(NewUserRequest newUserRequest);

    void deleteUser(long userId);

    List<UserDto> getAll(List<Long> ids, int from, int size);
}
