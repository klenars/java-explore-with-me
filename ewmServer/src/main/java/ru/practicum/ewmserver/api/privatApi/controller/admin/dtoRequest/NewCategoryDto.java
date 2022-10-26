package ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Класс новой категории, содержит поле {@link NewCategoryDto#name}
 */
@Getter
@Setter
public class NewCategoryDto {

    /**Название новой категории*/
    @NotBlank
    @Size(min = 2)
    private String name;
}
