package ru.practicum.ewmserver.dto.admin;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.dto.location.LocationDto;

@Getter
@Setter
public class AdminUpdateEventRequest {
    private String annotation;
    private long category;
    private String description;
    private String eventDate;
    private LocationDto location;
    private boolean paid;
    private int participantLimit;
    private boolean requestModeration;
    private String title;
}
