package ru.practicum.ewmserver.dto.user;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс ДТО Пользователь (краткая информация), содержит поля:
 * {@link UserShortDto#id},
 * {@link UserShortDto#name}
 */
@Getter
@Setter
public class UserShortDto {
    /**Идентификатор*/
    private Long id;
    /**Имя*/
    private String name;
}
