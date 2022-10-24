package ru.practicum.ewmserver.api.privatApi.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.api.privatApi.controller.admin.requestParams.AdminEventsRequestParams;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.AdminUpdateEventRequest;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.service.admin.AdminEventsService;

import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class AdminEventsController {

    private final AdminEventsService adminEventsService;

    @GetMapping
    public List<EventFullDto> getAll(AdminEventsRequestParams requestParams) {
        return adminEventsService.getAll(requestParams);
    }

    @PutMapping("/{eventId}")
    public EventFullDto updateEvent(
            @PathVariable long eventId,
            @RequestBody AdminUpdateEventRequest updateEventRequest
    ) {
        return adminEventsService.updateEvent(eventId, updateEventRequest);
    }

    @PatchMapping("/{eventId}/publish")
    public EventFullDto publishEvent(@PathVariable long eventId) {
        return adminEventsService.publishEvent(eventId);
    }

    @PatchMapping("/{eventId}/reject")
    public EventFullDto rejectEvent(@PathVariable long eventId) {
        return adminEventsService.rejectEvent(eventId);
    }
}
