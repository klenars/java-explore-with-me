package ru.practicum.ewmserver.dto.event;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.dto.location.LocationDto;
import ru.practicum.ewmserver.dto.user.UserShortDto;
import ru.practicum.ewmserver.entity.EventState;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventFullDto {
    private Long id;
    private CategoryDto category;
    private String title;
    private String annotation;
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private LocationDto location;
    private boolean paid;
    private int confirmedRequests;
    private LocalDateTime createdOn;
    private String description;
    private int participantLimit;
    private LocalDateTime publishedOn;
    private boolean requestModeration;
    private EventState state;
    private int views;
}
