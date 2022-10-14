package ru.practicum.ewmserver.controller.userPrivat;

import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/requests")
public class UserRequestsController {
    //TODO

    @GetMapping
    public List<ParticipationRequestDto> getAll(@PathVariable long userId) {
        //TODO
        return null;
    }

    @PostMapping
    public ParticipationRequestDto createRequest(
            @PathVariable long userId,
            @RequestParam long eventId
    ) {
        //TODO
        return null;
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(
            @PathVariable long userId,
            @PathVariable long requestId
    ) {
        //TODO
        return null;
    }
}
