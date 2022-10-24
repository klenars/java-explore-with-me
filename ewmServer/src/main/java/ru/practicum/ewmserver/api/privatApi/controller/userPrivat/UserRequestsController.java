package ru.practicum.ewmserver.api.privatApi.controller.userPrivat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;
import ru.practicum.ewmserver.service.user.UserRequestsService;
import ru.practicum.ewmserver.entity.Event;

import java.util.List;

/**
 * Закрытый API для работы с запросами текущего пользователя на участие в событиях {@link Event}, содержит поле:
 * {@link UserRequestsController#userRequestsService}
 */
@RestController
@RequestMapping("/users/{userId}/requests")
@RequiredArgsConstructor
public class UserRequestsController {

    /**Сервис обработки запросов по участию в событиях от пользователя*/
    private final UserRequestsService userRequestsService;

    /**
     * Получение информации о заявках текущего пользователя на участие в чужих событиях
     * @param userId id юзера
     * @return List of {@link ParticipationRequestDto}
     */
    @GetMapping
    public List<ParticipationRequestDto> getAll(@PathVariable long userId) {
        return userRequestsService.getAll(userId);
    }

    /**
     *Добавление запроса от текущего пользователя на участие в событии
     * @param userId id юзера
     * @param eventId id события
     * @return {@link ParticipationRequestDto}
     */
    @PostMapping
    public ParticipationRequestDto createRequest(
            @PathVariable long userId,
            @RequestParam long eventId
    ) {
        return userRequestsService.createRequest(userId, eventId);
    }

    /**
     * Отмена своего запроса на участие в событии
     * @param userId id юзера
     * @param requestId id запроса
     * @return {@link ParticipationRequestDto}
     */
    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(
            @PathVariable long userId,
            @PathVariable long requestId
    ) {
        return userRequestsService.cancelRequest(userId, requestId);
    }
}
