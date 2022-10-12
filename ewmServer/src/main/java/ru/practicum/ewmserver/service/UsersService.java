package ru.practicum.ewmserver.service;

import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.NewEventDto;

import java.util.List;

public interface UsersService {
    List<EventShortDto> getAll(long userId, int from, int size);

    EventFullDto createEvent(long userId, NewEventDto newEventDto);
}
