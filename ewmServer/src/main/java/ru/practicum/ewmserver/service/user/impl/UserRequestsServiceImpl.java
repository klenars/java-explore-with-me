package ru.practicum.ewmserver.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;
import ru.practicum.ewmserver.entity.*;
import ru.practicum.ewmserver.error.ForbiddenError;
import ru.practicum.ewmserver.mapper.ParticipationRequestMapper;
import ru.practicum.ewmserver.repository.EventRepository;
import ru.practicum.ewmserver.repository.RequestRepository;
import ru.practicum.ewmserver.repository.UserRepository;
import ru.practicum.ewmserver.service.user.UserRequestsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRequestsServiceImpl implements UserRequestsService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ParticipationRequestMapper requestMapper;

    @Override
    public ParticipationRequestDto createRequest(long userId, long eventId) {
        User user = userRepository.getById(userId);
        Event event = eventRepository.getEventById(eventId);
        checkDataForNewRequest(user, event);
        ParticipationRequest request = getNewRequest(user, event);

        return requestMapper.toDto(requestRepository.save(request));
    }

    @Override
    @Transactional
    public ParticipationRequestDto cancelRequest(long userId, long requestId) {
        ParticipationRequest request = requestRepository.getById(requestId);
        userRepository.getById(userId);

        if (request.getStatus().equals(RequestStatus.CONFIRMED)) {
            Event event = eventRepository.getEventById(request.getEvent().getId());
            event.setConfirmedRequests(event.getConfirmedRequests() - 1);
        }

        request.setStatus(RequestStatus.CANCELED);

        return requestMapper.toDto(request);
    }

    @Override
    public List<ParticipationRequestDto> getAll(long userId) {
        userRepository.getById(userId);
        return requestRepository.getAllByUserId(userId).stream()
                .map(requestMapper::toDto)
                .collect(Collectors.toList());
    }

    private ParticipationRequest getNewRequest(User user, Event event) {
        ParticipationRequest request = new ParticipationRequest();
        request.setEvent(event);
        request.setCreated(LocalDateTime.now());
        request.setRequester(user);
        if (event.isRequestModeration()) {
            request.setStatus(RequestStatus.PENDING);
        } else {
            request.setStatus(RequestStatus.CONFIRMED);
        }

        return request;
    }

    private void checkDataForNewRequest(User user, Event event) {
        if (requestRepository.requestAlreadyExist(event.getId(), user.getId())) {
            throw new ForbiddenError(
                    String.format(
                            "Запрос пользователя id=%d на участие в событии id=%d уже есть",
                            user.getId(),
                            event.getId()
                    )
            );
        }

        if (Objects.equals(user.getId(), event.getInitiator().getId())) {
            throw new ForbiddenError(
                    String.format(
                            "Пользователь id=%d является инициатором события id=%d",
                            user.getId(),
                            event.getId()
                    )
            );
        }

        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ForbiddenError(
                    String.format(
                            "Событие id=%d не опубликовано",
                            event.getId()
                    )
            );
        }

        if (requestRepository.quantityEventRequests(event.getId(), List.of(RequestStatus.PENDING, RequestStatus.CONFIRMED)) == event.getParticipantLimit()) {
            throw new ForbiddenError(
                    String.format(
                            "У события id=%d достигнут максимальный лимит запросов на участние",
                            event.getId()
                    )
            );
        }
    }
}
