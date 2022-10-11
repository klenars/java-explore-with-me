package ru.practicum.ewmserver.service;

import ru.practicum.ewmserver.dto.EventFullDto;
import ru.practicum.ewmserver.dto.EventShortDto;
import ru.practicum.ewmserver.dto.EventsRequestParamsDto;

import java.util.List;

public interface EventService {

    EventFullDto getEventById(long id);

    List<EventShortDto> getAllEvents(EventsRequestParamsDto params);
}
