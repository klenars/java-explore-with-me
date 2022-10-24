package ru.practicum.ewmserver.api.publicApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewmserver.api.publicApi.controller.requestParams.EventsRequestParams;
import ru.practicum.ewmserver.service.client.StatClient;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.service.publicSrv.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Публичный API для работы с событиями, имеет поля
 * {@link EventService},
 * {@link StatClient}
 */
@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsController {

    /**Сервис для обработки публичных запросов по событиям*/
    private final EventService eventService;

    /**Сервис-клиент для отправки данных статистики по обращению к эндпоинтам*/
    private final StatClient client;

    /**
     * Получение событий с возможностью фильтрации
     * @param params {@link EventsRequestParams}
     * @param request {@link HttpServletRequest}
     * @return List of {@link EventShortDto}
     */
    @GetMapping
    public List<EventShortDto> getAllEvents(
            EventsRequestParams params,
            HttpServletRequest request
    ) {
        log.info("GET with params: {}", params);
        List<EventShortDto> dtoList = eventService.getAllEvents(params);
        client.postHit(request);
        return dtoList;
    }

    /**
     * Получение подробной информации об опубликованном событии по его идентификатору
     * @param id id события
     * @param request {@link HttpServletRequest}
     * @return {@link EventFullDto}
     */
    @GetMapping("/{id}")
    public EventFullDto getEventById(
            @PathVariable long id,
            HttpServletRequest request
    ) {
        EventFullDto response = eventService.getEventById(id);
        client.postHit(request);
        return response;
    }
}
