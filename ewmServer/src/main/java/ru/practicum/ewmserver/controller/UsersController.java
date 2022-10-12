package ru.practicum.ewmserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.NewEventDto;
import ru.practicum.ewmserver.service.UsersService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping
    public List<EventShortDto> getAll(
            @PathVariable long userId,
            @RequestParam int from,
            @RequestParam int size
    ) {
        return usersService.getAll(userId, from, size);
    }

    @PostMapping
    public EventFullDto createEvent(
            @PathVariable long userId,
            @RequestBody NewEventDto newEventDto
    ) {
        return usersService.createEvent(userId, newEventDto);
    }
}
