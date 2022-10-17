package ru.practicum.ewmserver.service.admin.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmserver.dto.admin.AdminEventsRequestParams;
import ru.practicum.ewmserver.dto.admin.AdminUpdateEventRequest;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.location.LocationDto;
import ru.practicum.ewmserver.entity.Category;
import ru.practicum.ewmserver.entity.Event;
import ru.practicum.ewmserver.entity.EventState;
import ru.practicum.ewmserver.entity.Location;
import ru.practicum.ewmserver.error.ForbiddenError;
import ru.practicum.ewmserver.mapper.EventMapper;
import ru.practicum.ewmserver.repository.CategoryRepository;
import ru.practicum.ewmserver.repository.EventRepository;
import ru.practicum.ewmserver.repository.LocationRepository;
import ru.practicum.ewmserver.service.admin.AdminEventsService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminEventsServiceImpl implements AdminEventsService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    @Override
    public List<EventFullDto> getAll(AdminEventsRequestParams requestParams) {
        return eventRepository.findAll(requestParams.toSpecification(), requestParams.toPageable())
                .stream()
                .map(eventMapper::toFullDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EventFullDto publishEvent(long eventId) {
        Event event = eventRepository.getEventById(eventId);
        checkEventForPublish(event);
        event.setState(EventState.PUBLISHED);
        event.setPublishedOn(LocalDateTime.now());

        return eventMapper.toFullDto(event);
    }

    @Override
    @Transactional
    public EventFullDto rejectEvent(long eventId) {
        Event event = eventRepository.getEventById(eventId);
        checkEventForReject(event);
        event.setState(EventState.CANCELED);

        return eventMapper.toFullDto(event);
    }

    @Override
    @Transactional
    public EventFullDto updateEvent(long eventId, AdminUpdateEventRequest updateEventRequest) {
        log.info("get AdminUpdateEventRequest: {}", updateEventRequest);
        Event event = eventRepository.getEventById(eventId);
        log.info("event from bd: {}", event);
        updateEventFields(event, updateEventRequest);
        log.info("event after update: {}", event);

        return eventMapper.toFullDto(event);
    }

    private void updateEventFields(Event event, AdminUpdateEventRequest updateEventRequest) {
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
        }

        LocationDto location = updateEventRequest.getLocation();
        if (location != null) {
            Location newLocation = locationRepository.findById(event.getLocation().getId()).get();
            newLocation.setLat(location.getLat());
            newLocation.setLon(location.getLon());
        }

        Boolean paid = updateEventRequest.getPaid();
        if (paid != null) {
            event.setPaid(paid);
        }

        Integer participantLimit = updateEventRequest.getParticipantLimit();
        if (participantLimit != null) {
            event.setParticipantLimit(participantLimit);
        }

        Boolean requestModeration = updateEventRequest.getRequestModeration();
        if (requestModeration != null) {
            event.setRequestModeration(requestModeration);
        }

        String title = updateEventRequest.getTitle();
        if (title != null) {
            event.setTitle(title);
        }
    }

    private void checkEventForReject(Event event) {
        if (event.getState().equals(EventState.PUBLISHED)) {
            throw new ForbiddenError(
                    String.format("Событие id=%d (%s) уже опубликовано!", event.getId(), event.getTitle())
            );
        }
    }

    private void checkEventForPublish(Event event) {
        if (event.getEventDate().isBefore(LocalDateTime.now().plusHours(1))) {
            throw new ForbiddenError(
                    String.format("До события id=%d (%s) осталось меньше 1 часа!", event.getId(), event.getTitle())
            );
        }
        if (!event.getState().equals(EventState.PENDING)) {
            throw new ForbiddenError(
                    String.format("Событие id=%d (%s) должно быть в состоянии ожидания публикации!", event.getId(), event.getTitle())
            );
        }
    }
}
