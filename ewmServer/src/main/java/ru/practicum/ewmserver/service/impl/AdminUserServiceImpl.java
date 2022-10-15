package ru.practicum.ewmserver.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewmserver.dto.admin.NewUserRequest;
import ru.practicum.ewmserver.dto.user.UserDto;
import ru.practicum.ewmserver.entity.User;
import ru.practicum.ewmserver.mapper.UserMapper;
import ru.practicum.ewmserver.repository.UserRepository;
import ru.practicum.ewmserver.service.AdminUserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(NewUserRequest newUserRequest) {
        User newUser = userMapper.toEntity(newUserRequest);
        log.info("newUser after mapping: {}", newUser);

        newUser = userRepository.save(newUser);
        log.info("newUser after save: {}", newUser);

        return userMapper.toDto(newUser);
    }
}
