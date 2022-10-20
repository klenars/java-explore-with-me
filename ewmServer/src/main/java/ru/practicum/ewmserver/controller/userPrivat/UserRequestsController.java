package ru.practicum.ewmserver.controller.userPrivat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;
import ru.practicum.ewmserver.service.user.UserRequestsService;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/requests")
@RequiredArgsConstructor
public class UserRequestsController {

    private final UserRequestsService userRequestsService;

    @GetMapping
    public List<ParticipationRequestDto> getAll(@PathVariable long userId) {
        return userRequestsService.getAll(userId);
    }

    @PostMapping
    public ParticipationRequestDto createRequest(
            @PathVariable long userId,
            @RequestParam long eventId
    ) {
        return userRequestsService.createRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(
            @PathVariable long userId,
            @PathVariable long requestId
    ) {
        return userRequestsService.cancelRequest(userId, requestId);
    }
}
