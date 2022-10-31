package ru.practicum.ewmserver.api.privatApi.controller.userPrivat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoIn;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;
import ru.practicum.ewmserver.entity.Event;
import ru.practicum.ewmserver.service.user.UserFeedbackService;

/**
 * Закрытый API для работы с запросами текущего пользователя по отзывам на события {@link Event}, содержит поле:
 * {@link UserFeedbackController#userFeedbackService}
 */
@RestController
@RequestMapping("/users/{userId}/feedbacks")
@RequiredArgsConstructor
public class UserFeedbackController {

    /**Интерфейс сервиса обработки запросов по отзывам*/
    private final UserFeedbackService userFeedbackService;

    /**
     * Эндпоинт добавления отзыва
     * @param userId id текущего юзера
     * @param eventId id события
     * @param feedback {@link FeedbackDtoIn}
     * @return {@link FeedbackDtoOut}
     */
    @PostMapping("/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackDtoOut add(
            @PathVariable long userId,
            @PathVariable long eventId,
            @RequestBody FeedbackDtoIn feedback
    ) {
        return userFeedbackService.add(userId, eventId, feedback);
    }

    /**
     * Эндпоинт удаления юзером своего отзыва
     * @param userId id текущего юзера
     * @param feedId id события
     */
    @DeleteMapping("/{feedId}")
    public void delete(
        @PathVariable long userId,
        @PathVariable long feedId
    ) {
        userFeedbackService.delete(userId, feedId);
    }

    /**
     * Получение юзером своего отзыва по id
     * @param userId id текущего юзера
     * @param feedId id события
     * @return {@link FeedbackDtoOut}
     */
    @GetMapping("/{feedId}")
    public FeedbackDtoOut getById(
            @PathVariable long userId,
            @PathVariable long feedId
    ) {
        return userFeedbackService.getById(userId, feedId);
    }
}
