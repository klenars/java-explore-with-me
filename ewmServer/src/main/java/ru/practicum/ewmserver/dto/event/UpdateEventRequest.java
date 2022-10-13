package ru.practicum.ewmserver.dto.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEventRequest {
    private String annotation;
    private long category;
    private String description;
    private String eventDate;
    private long eventId;
    private boolean paid;
    private int participantLimit;
    private String title;
}
