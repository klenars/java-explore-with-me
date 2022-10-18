package ru.practicum.ewmserver.service.user;

import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;

import java.util.List;

public interface UserRequestsService {
    ParticipationRequestDto createRequest(long userId, long eventId);

    ParticipationRequestDto cancelRequest(long userId, long requestId);

    List<ParticipationRequestDto> getAll(long userId);
}
