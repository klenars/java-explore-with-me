package ru.practicum.ewmserver.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;
}
