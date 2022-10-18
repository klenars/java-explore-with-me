package ru.practicum.ewmserver.service.publicSrv;

import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.EventsRequestParams;

import java.util.List;

public interface EventService {

    EventFullDto getEventById(long id);

    List<EventShortDto> getAllEvents(EventsRequestParams params);
}
