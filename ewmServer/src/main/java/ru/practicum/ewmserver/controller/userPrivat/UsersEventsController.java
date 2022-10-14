package ru.practicum.ewmserver.controller.userPrivat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.NewEventDto;
import ru.practicum.ewmserver.dto.event.UpdateEventRequest;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;
import ru.practicum.ewmserver.service.UsersService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
public class UsersEventsController {

    private final UsersService usersService;

    @GetMapping
    public List<EventShortDto> getAll(
            @PathVariable long userId,
            @RequestParam int from,
            @RequestParam int size
    ) {
        return usersService.getAll(userId, from, size);
    }

    @PatchMapping
    public EventFullDto updateEvent(
            @PathVariable long userId,
            @RequestBody UpdateEventRequest updateEventRequest
    ) {
        return usersService.updateEvent(userId, updateEventRequest);
    }

    @PostMapping
    public EventFullDto createEvent(
            @PathVariable long userId,
            @RequestBody NewEventDto newEventDto
    ) {
        return usersService.createEvent(userId, newEventDto);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getById(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        return usersService.getById(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto cancelEvent(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        return usersService.cancelEvent(userId, eventId);
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getRequests(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        return usersService.getRequests(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto confirmRequest(
            @PathVariable long userId,
            @PathVariable long eventId,
            @PathVariable long reqId
    ) {
        return usersService.confirmRequest(userId, eventId, reqId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectRequest(
            @PathVariable long userId,
            @PathVariable long eventId,
            @PathVariable long reqId
    ) {
        return usersService.rejectRequest(userId, eventId, reqId);
    }
}
