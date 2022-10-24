package ru.practicum.ewmserver.service.admin;

import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewUserRequest;
import ru.practicum.ewmserver.dto.user.UserDto;

import java.util.List;

/**
 * Интерфейс сервиса обработки запросов по Юзерам от админа
 */
public interface AdminUserService {

    /**
     * Создать юзера
     * @param newUserRequest {@link NewUserRequest}
     * @return {@link UserDto}
     */
    UserDto createUser(NewUserRequest newUserRequest);

    /**
     * Удалить юзера
     * @param userId id юзера
     */
    void deleteUser(long userId);

    /**
     * Получить Юзеров по id
     * @param ids список id юзеров
     * @param from начальный индекс
     * @param size количество
     * @return List of {@link UserDto}
     */
    List<UserDto> getAll(List<Long> ids, int from, int size);
}
