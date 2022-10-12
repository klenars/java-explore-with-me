package ru.practicum.ewmserver.service;

import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.EventsRequestParamsDto;

import java.util.List;

public interface EventService {

    EventFullDto getEventById(long id);

    List<EventShortDto> getAllEvents(EventsRequestParamsDto params);
}
