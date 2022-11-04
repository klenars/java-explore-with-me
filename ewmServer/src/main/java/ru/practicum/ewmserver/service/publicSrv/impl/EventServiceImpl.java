package ru.practicum.ewmserver.service.publicSrv.impl;

import lombok.NonNull;
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
import ru.practicum.ewmserver.service.client.StatClient;
import ru.practicum.ewmserver.service.publicSrv.EventService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса {@link EventService}, имеет поля:
 * {@link EventServiceImpl#eventRepository},
 * {@link EventServiceImpl#eventMapper},
 */
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    /**Репозиторий событий {@link EventRepository}*/
    private final EventRepository eventRepository;

    /**Маппер событий {@link EventMapper}*/
    private final EventMapper eventMapper;

    private final StatClient statClient;

    @Override
    @Transactional
    public EventFullDto getEventById(long id) {
        Event event = eventRepository.getEventById(id);
        checkEventStatus(event);

        var views = statClient.getViewsMap(List.of(event));
        var dto = eventMapper.toFullDto(event);
        dto.setViews(views.getOrDefault(dto.getId(), 0L));

        return dto;
    }

    @Override
    public List<EventShortDto> getAllEvents(@NonNull EventsRequestParams params) {
        var events = eventRepository.findAll(params.toSpecification(), params.toPageable());
        var views = statClient.getViewsMap(events.toList());

        return events.stream()
                .map(eventMapper::toShortDto)
                .peek(dto -> dto.setViews(views.getOrDefault(dto.getId(), 0L)))
                .collect(Collectors.toList());
    }

    /**
     * Проверка статуса события перед получением
     * @param event {@link Event}
     * @throws ForbiddenError
     */
    private void checkEventStatus(@NonNull Event event) {
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ForbiddenError(
                    String.format("Событие id=%d не опубликовано", event.getId())
            );
        }
    }
}
