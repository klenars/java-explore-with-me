package ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import ru.practicum.ewmserver.entity.User;

/**
 * Класс создания нового Пользователя {@link User}, содержит поля:
 * {@link NewUserRequest#email},
 * {@link NewUserRequest#name}
 */
@Getter
@Setter
@ToString
public class NewUserRequest {

    /**Адрес электронной почты юзера*/
    @Email
    private String email;

    /**Имя юзера*/
    @NotBlank
    @Size(min = 2)
    private String name;
}
