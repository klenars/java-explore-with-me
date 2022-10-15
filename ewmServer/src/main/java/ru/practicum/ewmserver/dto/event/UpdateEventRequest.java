package ru.practicum.ewmserver.dto.event;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateEventRequest {
    private String annotation;
    private long category;
    private String description;
    private String eventDate;
    @NotNull
    private Long eventId;
    private boolean paid;
    private int participantLimit;
    private String title;
}