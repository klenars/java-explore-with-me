package ru.practicum.ewmserver.api.privatApi.controller.userPrivat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoIn;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;
import ru.practicum.ewmserver.service.user.UserFeedbackService;

@RestController
@RequestMapping("/users/{userId}/feedbacks")
@RequiredArgsConstructor
public class UserFeedbackController {

    private final UserFeedbackService userFeedbackService;

    @PostMapping("/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackDtoOut add(
            @PathVariable long userId,
            @PathVariable long eventId,
            @RequestBody FeedbackDtoIn feedback
    ) {
        return userFeedbackService.add(userId, eventId, feedback);
    }

    @DeleteMapping("/{feedId}")
    public void delete(
        @PathVariable long userId,
        @PathVariable long feedId
    ) {
        userFeedbackService.delete(userId, feedId);
    }

    @GetMapping("/{feedId}")
    public FeedbackDtoOut getById(
            @PathVariable long userId,
            @PathVariable long feedId
    ) {
        return userFeedbackService.getById(userId, feedId);
    }
}
