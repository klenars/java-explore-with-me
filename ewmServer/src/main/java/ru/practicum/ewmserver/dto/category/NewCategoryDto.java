package ru.practicum.ewmserver.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class NewCategoryDto {
    @NotBlank
    @Size(min = 2)
    private String name;
}
