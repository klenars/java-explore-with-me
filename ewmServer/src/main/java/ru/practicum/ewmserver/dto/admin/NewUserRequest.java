package ru.practicum.ewmserver.dto.admin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewUserRequest {

    @NotNull
    private String email;

    @NotNull
    private String name;
}
