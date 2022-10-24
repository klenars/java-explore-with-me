package ru.practicum.ewmserver.service.admin;

import ru.practicum.ewmserver.api.privatApi.controller.admin.requestParams.AdminEventsRequestParams;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.AdminUpdateEventRequest;
import ru.practicum.ewmserver.dto.event.EventFullDto;

import java.util.List;

public interface AdminEventsService {
    List<EventFullDto> getAll(AdminEventsRequestParams requestParams);

    EventFullDto publishEvent(long eventId);

    EventFullDto rejectEvent(long eventId);

    EventFullDto updateEvent(long eventId, AdminUpdateEventRequest updateEventRequest);
}
