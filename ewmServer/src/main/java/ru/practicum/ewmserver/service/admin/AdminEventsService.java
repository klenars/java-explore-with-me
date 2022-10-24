package ru.practicum.ewmserver.service.admin;

import ru.practicum.ewmserver.api.privatApi.controller.admin.requestParams.AdminEventsRequestParams;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.AdminUpdateEventRequest;
import ru.practicum.ewmserver.dto.event.EventFullDto;

import java.util.List;

/**
 * Интерфейс сервиса обработки запросов по Событиям от админа
 */
public interface AdminEventsService {

    /**
     * Получить список событий по параметрам
     * @param requestParams {@link AdminEventsRequestParams}
     * @return List of {@link EventFullDto}
     */
    List<EventFullDto> getAll(AdminEventsRequestParams requestParams);

    /**
     * Опублтковать событие
     * @param eventId id события
     * @return {@link EventFullDto}
     */
    EventFullDto publishEvent(long eventId);

    /**
     * Отклонить событие
     * @param eventId id события
     * @return {@link EventFullDto}
     */
    EventFullDto rejectEvent(long eventId);

    /**
     * Обновить событие
     * @param eventId id события
     * @param updateEventRequest {@link AdminUpdateEventRequest}
     * @return {@link EventFullDto}
     */
    EventFullDto updateEvent(long eventId, AdminUpdateEventRequest updateEventRequest);
}
