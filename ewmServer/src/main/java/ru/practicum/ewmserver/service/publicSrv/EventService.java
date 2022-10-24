package ru.practicum.ewmserver.service.publicSrv;

import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.api.publicApi.controller.requestParams.EventsRequestParams;

import java.util.List;

/**
 * Интерфейс публичного сервиса Событий {@link ru.practicum.ewmserver.entity.Event}
 */
public interface EventService {

    /**
     * Получение события по id
     * @param id id события
     * @return {@link EventFullDto}
     */
    EventFullDto getEventById(long id);

    /**
     * Получение списка событий по параметрам
     * @param params {@link EventsRequestParams}
     * @return List of {@link EventShortDto}
     */
    List<EventShortDto> getAllEvents(EventsRequestParams params);
}
