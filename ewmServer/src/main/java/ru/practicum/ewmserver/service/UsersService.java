package ru.practicum.ewmserver.service;

import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.NewEventDto;
import ru.practicum.ewmserver.dto.event.UpdateEventRequest;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;

import java.util.List;

public interface UsersService {
    List<EventShortDto> getAll(long userId, int from, int size);

    EventFullDto createEvent(long userId, NewEventDto newEventDto);

    EventFullDto updateEvent(long userId, UpdateEventRequest updateEventRequest);

    EventFullDto getById(long userId, long eventId);

    EventFullDto cancelEvent(long userId, long eventId);

    List<ParticipationRequestDto> getRequests(long userId, long eventId);

    ParticipationRequestDto confirmRequest(long userId, long eventId, long reqId);

    ParticipationRequestDto rejectRequest(long userId, long eventId, long reqId);
}
