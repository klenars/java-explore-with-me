package ru.practicum.ewmserver.dto.compilation;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.dto.event.EventShortDto;

import java.util.List;

@Getter
@Setter
public class CompilationDto {
    private long id;
    private String title;
    private boolean pinned;
    private List<EventShortDto> events;
}
