package ru.practicum.ewmserver.api.privatApi.controller.userPrivat.dtoRequest;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateEventRequest {
    private String annotation;
    private Long category;
    private String description;
    private String eventDate;
    @NotNull
    private Long eventId;
    private Boolean paid;
    private Integer participantLimit;
    private String title;
}
