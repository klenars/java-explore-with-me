package ru.practicum.ewmserver.dto.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.ewmserver.dto.location.LocationDto;

@Getter
@Setter
@ToString
public class AdminUpdateEventRequest {
    private String annotation;
    private Long category;
    private String description;
    private String eventDate;
    private LocationDto location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private String title;
}
