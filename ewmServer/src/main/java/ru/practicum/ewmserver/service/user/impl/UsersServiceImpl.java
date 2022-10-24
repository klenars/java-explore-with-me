package ru.practicum.ewmserver.service.user.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.api.privatApi.controller.userPrivat.dtoRequest.NewEventDto;
import ru.practicum.ewmserver.api.privatApi.controller.userPrivat.dtoRequest.UpdateEventRequest;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;
import ru.practicum.ewmserver.entity.*;
import ru.practicum.ewmserver.error.ForbiddenError;
import ru.practicum.ewmserver.mapper.EventMapper;
import ru.practicum.ewmserver.mapper.ParticipationRequestMapper;
import ru.practicum.ewmserver.repository.*;
import ru.practicum.ewmserver.service.user.UsersService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final RequestRepository requestRepository;
    private final ParticipationRequestMapper requestMapper;

    @Override
    public List<EventShortDto> getAll(long userId, int from, int size) {
        userRepository.getById(userId);
        return eventRepository.findByInitiatorId(userId, PageRequest.of(from / size, size)).stream()
                .map(eventMapper::toShortDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EventFullDto createEvent(long userId, NewEventDto newEventDto) {
        User initiator = userRepository.getById(userId);
        Category category = categoryRepository.getById(newEventDto.getCategory());
        Event newEvent = eventMapper.toEntity(newEventDto, category, initiator);
        checkNewEventDate(newEvent);
        log.info("newEvent after mapping from NewEventDto: {}", newEvent);

        Location location = locationRepository.save(newEvent.getLocation());
        newEvent = eventRepository.save(newEvent);
        location.setEvent(newEvent);
        log.info("newEvent after save: {}", newEvent);

        return eventMapper.toFullDto(newEvent);
    }

    @Override
    @Transactional
    public EventFullDto updateEvent(long userId, UpdateEventRequest updateEventRequest) {
        Event eventForUpdate = eventRepository.getEventById(updateEventRequest.getEventId());
        checkEventState(eventForUpdate);
        updateEventFields(eventForUpdate, updateEventRequest);
        return eventMapper.toFullDto(eventForUpdate);
    }


    @Override
    public EventFullDto getById(long userId, long eventId) {
        User user = userRepository.getById(userId);
        Event event = eventRepository.getEventById(eventId);
        checkEventInitiator(user, event);

        return eventMapper.toFullDto(event);
    }

    @Override
    @Transactional
    public EventFullDto cancelEvent(long userId, long eventId) {
        Event event = eventRepository.getEventById(eventId);
        User user = userRepository.getById(userId);
        checkEventInitiator(user, event);
        checkEventState(event);

        event.setState(EventState.CANCELED);

        return eventMapper.toFullDto(event);
    }

    @Override
    public List<ParticipationRequestDto> getRequests(long userId, long eventId) {
        User user = userRepository.getById(userId);
        Event event = eventRepository.getEventById(eventId);
        checkEventInitiator(user, event);

        return requestRepository.getAllByEventId(eventId).stream()
                .map(requestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ParticipationRequestDto confirmRequest(long userId, long eventId, long reqId) {
        User user = userRepository.getById(userId);
        Event event = eventRepository.getEventById(eventId);
        checkEventInitiator(user, event);
        ParticipationRequest request = requestRepository.getById(reqId);
        if (event.getParticipantLimit() == 0 || !event.isRequestModeration()) {
            request.setStatus(RequestStatus.CONFIRMED);
            return requestMapper.toDto(request);
        }
        checkRequestLimit(event);

        request.setStatus(RequestStatus.CONFIRMED);

        event.setConfirmedRequests(event.getConfirmedRequests() + 1);

        if (requestRepository.quantityEventRequests(event.getId(), List.of(RequestStatus.CONFIRMED)) == event.getParticipantLimit()) {
            requestRepository.getAllByEventIdAndStatus(eventId, RequestStatus.PENDING)
                    .forEach(r -> r.setStatus(RequestStatus.CANCELED));
        }

        return requestMapper.toDto(request);
    }

    @Override
    @Transactional
    public ParticipationRequestDto rejectRequest(long userId, long eventId, long reqId) {
        User user = userRepository.getById(userId);
        Event event = eventRepository.getEventById(eventId);
        checkEventInitiator(user, event);
        ParticipationRequest request = requestRepository.getById(reqId);

        if (request.getStatus().equals(RequestStatus.CONFIRMED)) {
            event.setConfirmedRequests(event.getConfirmedRequests() - 1);
        }

        request.setStatus(RequestStatus.REJECTED);

        return requestMapper.toDto(request);
    }

    private void checkRequestLimit(Event event) {
        if (requestRepository.quantityEventRequests(event.getId(), List.of(RequestStatus.CONFIRMED)) == event.getParticipantLimit()) {
            throw new ForbiddenError(
                    String.format(
                            "У события id=%d достигнут максимальный лимит участников",
                            event.getId()
                    )
            );
        }
    }

    private void checkEventInitiator(User user, Event event) {
        if (!Objects.equals(user.getId(), event.getInitiator().getId())) {
            throw new ForbiddenError(
                    String.format("Юзер id=%d не является инициатором события id=%d", user.getId(), event.getId())
            );
        }
    }

    private void checkNewEventDate(Event newEvent) {
        if (newEvent.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new ForbiddenError(
                    String.format("До события %s осталось меньше 2 часов!", newEvent.getTitle())
            );
        }
    }

    private void checkEventState(Event event) {
        if (event.getState().equals(EventState.PUBLISHED)) {
            throw new ForbiddenError(
                    String.format("Событие %s уже опубликовано!", event.getTitle())
            );
        }
    }

    private void updateEventFields(Event event, UpdateEventRequest updateEventRequest) {

        event.setState(EventState.PENDING);

        String annotation = updateEventRequest.getAnnotation();
        if (annotation != null) {
            event.setAnnotation(annotation);
        }

        Long categoryId = updateEventRequest.getCategory();
        if (categoryId != null) {
            Category newCategory = categoryRepository.getById(categoryId);
            event.setCategory(newCategory);
        }

        String description = updateEventRequest.getDescription();
        if (description != null) {
            event.setDescription(description);
        }

        String eventDate = updateEventRequest.getEventDate();
        if (eventDate != null) {
            event.setEventDate(LocalDateTime.parse(eventDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            checkNewEventDate(event);
        }

        Boolean paid = updateEventRequest.getPaid();
        if (paid != null) {
            event.setPaid(paid);
        }

        Integer participantLimit = updateEventRequest.getParticipantLimit();
        if (participantLimit != null) {
            event.setParticipantLimit(participantLimit);
        }

        String title = updateEventRequest.getTitle();
        if (title != null) {
            event.setTitle(title);
        }
    }
}
