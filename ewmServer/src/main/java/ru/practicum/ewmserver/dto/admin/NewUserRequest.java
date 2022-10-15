package ru.practicum.ewmserver.dto.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class NewUserRequest {

    @Email
    private String email;

    @NotBlank
    @Size(min = 2)
    private String name;
}
