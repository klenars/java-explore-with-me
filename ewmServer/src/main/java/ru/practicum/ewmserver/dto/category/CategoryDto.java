package ru.practicum.ewmserver.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Класс Категория ДТО, содержит поля:
 * {@link CategoryDto#id},
 * {@link CategoryDto#name}
 */
@Getter
@Setter
public class CategoryDto {

    /**Идентификатор категории*/
    @NotNull
    private Long id;

    /**Название категории*/
    @NotBlank
    private String name;
}
