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

@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsController {

    private final EventService eventService;
    private final StatClient client;

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
