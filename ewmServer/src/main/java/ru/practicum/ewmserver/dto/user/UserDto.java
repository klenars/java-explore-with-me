package ru.practicum.ewmserver.dto.user;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс ДТО Пользователь, содержит поля:
 * {@link UserDto#id},
 * {@link UserDto#name},
 * {@link UserDto#email},
 */
@Getter
@Setter
public class UserDto {
    /**Идентификатор*/
    private Long id;
    /**Имя*/
    private String name;
    /**Почтовый адрес*/
    private String email;
    /**Рейтинг*/
    private Double rating;
}
