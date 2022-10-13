package ru.practicum.ewmserver.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.NewEventDto;
import ru.practicum.ewmserver.dto.event.UpdateEventRequest;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;
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

    @Override
    public EventFullDto updateEvent(long userId, UpdateEventRequest updateEventRequest) {
        return null;
    }

    @Override
    public EventFullDto getById(long userId, long eventId) {
        return null;
    }

    @Override
    public EventFullDto cancelEvent(long userId, long eventId) {
        return null;
    }

    @Override
    public List<ParticipationRequestDto> getRequests(long userId, long eventId) {
        return null;
    }

    @Override
    public ParticipationRequestDto confirmRequest(long userId, long eventId, long reqId) {
        return null;
    }

    @Override
    public ParticipationRequestDto rejectRequest(long userId, long eventId, long reqId) {
        return null;
    }
}
