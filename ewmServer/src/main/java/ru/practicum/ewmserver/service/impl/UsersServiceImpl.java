package ru.practicum.ewmserver.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.NewEventDto;
import ru.practicum.ewmserver.service.UsersService;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {



    @Override
    public List<EventShortDto> getAll(long userId, int from, int size) {
        return null;
    }

    @Override
    public EventFullDto createEvent(long userId, NewEventDto newEventDto) {
        return null;
    }
}
