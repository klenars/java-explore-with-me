package ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class NewCompilationDto {

    @NotNull
    private String title;
    private List<Long> events;
    private boolean pinned;
}
