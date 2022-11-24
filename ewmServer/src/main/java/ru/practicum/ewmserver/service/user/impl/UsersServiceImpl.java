package ru.practicum.ewmserver.service.user.impl;

import lombok.NonNull;
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

/**
 * Реализация интерфейса {@link UsersService}, имеет поля:
 * {@link UsersServiceImpl#eventRepository},
 * {@link UsersServiceImpl#eventMapper},
 * {@link UsersServiceImpl#userRepository},
 * {@link UsersServiceImpl#categoryRepository},
 * {@link UsersServiceImpl#locationRepository},
 * {@link UsersServiceImpl#requestRepository},
 * {@link UsersServiceImpl#requestMapper},
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    /**Репозиторий Событий {@link EventRepository}*/
    private final EventRepository eventRepository;

    /**Маппер Событий {@link EventMapper}*/
    private final EventMapper eventMapper;

    /**Репозиторий Юзеров {@link UserRepository}*/
    private final UserRepository userRepository;

    /**Репозиторий Категорий {@link CategoryRepository}*/
    private final CategoryRepository categoryRepository;

    /**Репозиторий Локаций {@link LocationRepository}*/
    private final LocationRepository locationRepository;

    /**Репозиторий Запросов на участие {@link RequestRepository}*/
    private final RequestRepository requestRepository;

    /**Маппер Запросов на участие {@link ParticipationRequestMapper}*/
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
    public EventFullDto createEvent(long userId, @NonNull NewEventDto newEventDto) {
        User initiator = userRepository.getById(userId);
        Category category = categoryRepository.getById(newEventDto.getCategory());
        Event newEvent = eventMapper.toEntity(newEventDto, category, initiator);
//        TODO закоментировнно для теста фичи (для создания сыбытий в прошлом)
//        checkNewEventDate(newEvent);
        log.info("newEvent after mapping from NewEventDto: {}", newEvent);

        Location location = locationRepository.save(newEvent.getLocation());
        newEvent = eventRepository.save(newEvent);
        location.setEvent(newEvent);
        log.info("newEvent after save: {}", newEvent);

        return eventMapper.toFullDto(newEvent);
    }

    @Override
    @Transactional
    public EventFullDto updateEvent(long userId, @NonNull UpdateEventRequest updateEventRequest) {
        Event eventForUpdate = eventRepository.getById(updateEventRequest.getEventId());
        checkEventState(eventForUpdate);
        updateEventFields(eventForUpdate, updateEventRequest);
        return eventMapper.toFullDto(eventForUpdate);
    }


    @Override
    public EventFullDto getById(long userId, long eventId) {
        User user = userRepository.getById(userId);
        Event event = eventRepository.getById(eventId);
        checkEventInitiator(user, event);

        return eventMapper.toFullDto(event);
    }

    @Override
    @Transactional
    public EventFullDto cancelEvent(long userId, long eventId) {
        Event event = eventRepository.getById(eventId);
        User user = userRepository.getById(userId);
        checkEventInitiator(user, event);
        checkEventState(event);

        event.setState(EventState.CANCELED);

        return eventMapper.toFullDto(event);
    }

    @Override
    public List<ParticipationRequestDto> getRequests(long userId, long eventId) {
        User user = userRepository.getById(userId);
        Event event = eventRepository.getById(eventId);
        checkEventInitiator(user, event);

        return requestRepository.getAllByEventId(eventId).stream()
                .map(requestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ParticipationRequestDto confirmRequest(long userId, long eventId, long reqId) {
        User user = userRepository.getById(userId);
        Event event = eventRepository.getById(eventId);
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
        Event event = eventRepository.getById(eventId);
        checkEventInitiator(user, event);
        ParticipationRequest request = requestRepository.getById(reqId);

        if (request.getStatus().equals(RequestStatus.CONFIRMED)) {
            event.setConfirmedRequests(event.getConfirmedRequests() - 1);
        }

        request.setStatus(RequestStatus.REJECTED);

        return requestMapper.toDto(request);
    }

    /**
     * Проверка лимита участников в событии
     * @param event {@link Event}
     */
    private void checkRequestLimit(@NonNull Event event) {
        if (requestRepository.quantityEventRequests(event.getId(), List.of(RequestStatus.CONFIRMED)) == event.getParticipantLimit()) {
            throw new ForbiddenError(
                    String.format(
                            "У события id=%d достигнут максимальный лимит участников",
                            event.getId()
                    )
            );
        }
    }

    /**
     * Проверка инициатора события
     * @param user {@link User}
     * @param event {@link Event}
     */
    private void checkEventInitiator(@NonNull User user, @NonNull Event event) {
        if (!Objects.equals(user.getId(), event.getInitiator().getId())) {
            throw new ForbiddenError(
                    String.format("Юзер id=%d не является инициатором события id=%d", user.getId(), event.getId())
            );
        }
    }

    /**
     * Проверка даты нового события
     * @param newEvent {@link Event}
     */
    private void checkNewEventDate(@NonNull Event newEvent) {
        if (newEvent.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new ForbiddenError(
                    String.format("До события %s осталось меньше 2 часов!", newEvent.getTitle())
            );
        }
    }

    /**
     * Проверка статуса события
     * @param event {@link Event}
     */
    private void checkEventState(@NonNull Event event) {
        if (event.getState().equals(EventState.PUBLISHED)) {
            throw new ForbiddenError(
                    String.format("Событие %s уже опубликовано!", event.getTitle())
            );
        }
    }

    /**
     * Обновление полей события
     * @param event {@link Event}
     * @param updateEventRequest {@link UpdateEventRequest}
     */
    private void updateEventFields(@NonNull Event event, @NonNull UpdateEventRequest updateEventRequest) {

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
