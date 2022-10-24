package ru.practicum.ewmserver.api.privatApi.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.api.privatApi.controller.admin.requestParams.AdminEventsRequestParams;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.AdminUpdateEventRequest;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.service.admin.AdminEventsService;
import ru.practicum.ewmserver.entity.Event;

import java.util.List;

/**
 * API админа для работы с событиями {@link Event}, имеет поле:
 * {@link AdminEventsController#adminEventsService}
 */
@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class AdminEventsController {

    /**Сервис обработки запросов по Событиям от админа*/
    private final AdminEventsService adminEventsService;

    /**
     *Поиск событий
     * @param requestParams {@link AdminEventsRequestParams}
     * @return List of {@link EventFullDto}
     */
    @GetMapping
    public List<EventFullDto> getAll(AdminEventsRequestParams requestParams) {
        return adminEventsService.getAll(requestParams);
    }

    /**
     * Редактирование события
     * @param eventId id события
     * @param updateEventRequest {@link AdminUpdateEventRequest}
     * @return {@link EventFullDto}
     */
    @PutMapping("/{eventId}")
    public EventFullDto updateEvent(
            @PathVariable long eventId,
            @RequestBody AdminUpdateEventRequest updateEventRequest
    ) {
        return adminEventsService.updateEvent(eventId, updateEventRequest);
    }

    /**
     * Публикация события
     * @param eventId id события
     * @return {@link EventFullDto}
     */
    @PatchMapping("/{eventId}/publish")
    public EventFullDto publishEvent(@PathVariable long eventId) {
        return adminEventsService.publishEvent(eventId);
    }

    /**
     * Отклонение события
     * @param eventId id события
     * @return {@link EventFullDto}
     */
    @PatchMapping("/{eventId}/reject")
    public EventFullDto rejectEvent(@PathVariable long eventId) {
        return adminEventsService.rejectEvent(eventId);
    }
}
