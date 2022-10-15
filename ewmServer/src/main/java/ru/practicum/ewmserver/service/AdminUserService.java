package ru.practicum.ewmserver.service;

import ru.practicum.ewmserver.dto.admin.NewUserRequest;
import ru.practicum.ewmserver.dto.user.UserDto;

public interface AdminUserService {
    UserDto createUser(NewUserRequest newUserRequest);
}
