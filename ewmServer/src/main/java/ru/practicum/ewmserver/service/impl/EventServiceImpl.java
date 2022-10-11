package ru.practicum.ewmserver.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.ewmserver.dto.EventFullDto;
import ru.practicum.ewmserver.dto.EventShortDto;
import ru.practicum.ewmserver.dto.EventsRequestParamsDto;
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
