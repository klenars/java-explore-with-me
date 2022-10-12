package ru.practicum.ewmserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.EventsRequestParamsDto;
import ru.practicum.ewmserver.service.EventService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsController {

    private final EventService eventService;

    //http://localhost:8080/events?text=text&categories=1&categories=2&categories=3&paid=true&rangeStart="2025-09-13 21:00:00"&rangeEnd="2025-09-13 21:00:00"&onlyAvailable=true&sort=EVENT_DATE&from=20&size=10
    @GetMapping
    public List<EventShortDto> getAllEvents(EventsRequestParamsDto params) {
        log.info("GET with params: {}", params);
        return eventService.getAllEvents(params);
    }

    @GetMapping("/{id}")
    public EventFullDto getEventById(@PathVariable long id) {
        return eventService.getEventById(id);
    }
}
