package ru.practicum.ewmserver.dto.event;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.dto.user.UserShortDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventShortDto {
    private Long id;
    private CategoryDto category;
    private String title;
    private String annotation;
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private boolean paid;
    private int confirmedRequests;
    private int views;
}
