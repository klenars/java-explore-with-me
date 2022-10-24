package ru.practicum.ewmserver.api.privatApi.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewUserRequest;
import ru.practicum.ewmserver.dto.user.UserDto;
import ru.practicum.ewmserver.service.admin.AdminUserService;
import ru.practicum.ewmserver.entity.User;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * API админа для работы с пользователями {@link User}, содержит поле:
 * {@link AdminUsersController#adminUserService}
 */
@Slf4j
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
public class AdminUsersController {

    /**Сервис обработки запросов по Юзерам от админа*/
    private final AdminUserService adminUserService;

    /**
     * Получение информации о пользователях
     * @param ids список id пользователей
     * @param from количество элементов, которые нужно пропустить для формирования текущего набора
     * @param size количество элементов в наборе
     * @return List of {@link UserDto}
     */
    @GetMapping
    public List<UserDto> getAll(
            @RequestParam List<Long> ids,
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size
    ) {
        return adminUserService.getAll(ids, from, size);
    }

    /**
     *Добавление нового пользователя
     * @param newUserRequest {@link NewUserRequest}
     * @return {@link UserDto}
     */
    @PostMapping
    public UserDto createUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        log.info("adminUsersController POST createUser got NewUserRequest: {}", newUserRequest);

        return adminUserService.createUser(newUserRequest);
    }

    /**
     * Удаление пользователя
     * @param userId id пользователя
     */
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        adminUserService.deleteUser(userId);
    }
}
