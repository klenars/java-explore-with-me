package ru.practicum.ewmserver.dto.compilation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class NewCompilationDto {

    @NotNull
    private String title;
    private List<Long> events;
    private boolean pinned;
}
