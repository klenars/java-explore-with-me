package ru.practicum.ewmserver.service.admin;

import ru.practicum.ewmserver.dto.admin.AdminEventsRequestParams;
import ru.practicum.ewmserver.dto.admin.AdminUpdateEventRequest;
import ru.practicum.ewmserver.dto.event.EventFullDto;

import java.util.List;

public interface AdminEventsService {
    List<EventFullDto> getAll(AdminEventsRequestParams requestParams);

    EventFullDto publishEvent(long eventId);

    EventFullDto rejectEvent(long eventId);

    EventFullDto updateEvent(long eventId, AdminUpdateEventRequest updateEventRequest);
}
