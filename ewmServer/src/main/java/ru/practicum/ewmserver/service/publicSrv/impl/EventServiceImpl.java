package ru.practicum.ewmserver.service.publicSrv.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.api.publicApi.controller.requestParams.EventsRequestParams;
import ru.practicum.ewmserver.entity.Event;
import ru.practicum.ewmserver.entity.EventState;
import ru.practicum.ewmserver.error.ForbiddenError;
import ru.practicum.ewmserver.mapper.EventMapper;
import ru.practicum.ewmserver.repository.EventRepository;
import ru.practicum.ewmserver.service.publicSrv.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    @Transactional
    public EventFullDto getEventById(long id) {
        Event event = eventRepository.getEventById(id);
        checkEventStatus(event);
        event.setViews(event.getViews() + 1);

        return eventMapper.toFullDto(event);
    }

    @Override
    public List<EventShortDto> getAllEvents(EventsRequestParams params) {
        return eventRepository.findAll(params.toSpecification(), params.toPageable()).stream()
                .map(eventMapper::toShortDto)
                .collect(Collectors.toList());
    }

    private void checkEventStatus(Event event) {
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ForbiddenError(
                    String.format("Событие id=%d не опубликовано", event.getId())
            );
        }
    }
}
