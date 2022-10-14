package ru.practicum.ewmserver.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.admin.AdminEventsRequestParams;
import ru.practicum.ewmserver.dto.admin.AdminUpdateEventRequest;
import ru.practicum.ewmserver.dto.event.EventFullDto;

import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class adminEventsController {

//TODO

    @GetMapping
    public List<EventFullDto> getAll(AdminEventsRequestParams requestParams) {
        //TODO
        return null;
    }

    @PutMapping("/{eventId}")
    public EventFullDto updateEvent(
            @PathVariable long eventId,
            AdminUpdateEventRequest updateEventRequest
    ) {
        //TODO
        return null;
    }

    @PatchMapping("/{eventId}/publish")
    public EventFullDto publishEvent(@PathVariable long eventId) {
        //TODO
        return null;
    }

    @PatchMapping("/{eventId}/reject")
    public EventFullDto rejectEvent(@PathVariable long eventId) {
        //TODO
        return null;
    }
}
