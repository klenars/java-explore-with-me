package ru.practicum.ewmserver.service.publicSrv.impl;

import org.springframework.stereotype.Service;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.EventsRequestParams;
import ru.practicum.ewmserver.service.publicSrv.EventService;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public EventFullDto getEventById(long id) {
        return null;
    }

    @Override
    public List<EventShortDto> getAllEvents(EventsRequestParams params) {
        return null;
    }
}
