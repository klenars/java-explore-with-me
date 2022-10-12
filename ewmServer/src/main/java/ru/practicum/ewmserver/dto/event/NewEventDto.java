package ru.practicum.ewmserver.dto.event;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.dto.location.LocationDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class NewEventDto {
    private String annotation;
    private long category;
    private String description;
    private LocalDateTime eventDate;
    private LocationDto location;
    private boolean paid;
    private int participantLimit;
    private boolean requestModeration;
    private String title;
}
