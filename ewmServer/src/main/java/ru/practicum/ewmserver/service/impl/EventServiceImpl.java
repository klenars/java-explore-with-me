package ru.practicum.ewmserver.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.EventsRequestParamsDto;
import ru.practicum.ewmserver.service.EventService;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public EventFullDto getEventById(long id) {
        return null;
    }

    @Override
    public List<EventShortDto> getAllEvents(EventsRequestParamsDto params) {
        return null;
    }
}
