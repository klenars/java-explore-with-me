package ru.practicum.ewmserver.api.privatApi.controller.userPrivat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.api.privatApi.controller.userPrivat.dtoRequest.NewEventDto;
import ru.practicum.ewmserver.api.privatApi.controller.userPrivat.dtoRequest.UpdateEventRequest;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;
import ru.practicum.ewmserver.service.user.UsersService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Закрытый API для работы с событиями юзера, имеет поле:
 * {@link UsersService}
 */
@Slf4j
@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
@Validated
public class UsersEventsController {

    /**Сервис обработки запросов по событиям от пользователя*/
    private final UsersService usersService;

    /**
     *Получение событий, добавленных текущим пользователем
     * @param userId id текущего пользователя
     * @param from количество элементов, которые нужно пропустить для формирования текущего набора
     * @param size количество элементов в наборе
     * @return List of {@link EventShortDto}
     */
    @GetMapping
    public List<EventShortDto> getAll(
            @PathVariable long userId,
            @PositiveOrZero @RequestParam int from,
            @Positive @RequestParam int size
    ) {
        return usersService.getAll(userId, from, size);
    }

    /**
     * Изменение события добавленного текущим пользователем
     * @param userId id текущего пользователя
     * @param updateEventRequest {@link UpdateEventRequest}
     * @return {@link EventFullDto}
     */
    @PatchMapping
    public EventFullDto updateEvent(
            @PathVariable long userId,
            @RequestBody UpdateEventRequest updateEventRequest
    ) {
        return usersService.updateEvent(userId, updateEventRequest);
    }

    /**
     * Добавление нового события
     * @param userId id текущего пользователя
     * @param newEventDto {@link NewEventDto}
     * @return {@link EventFullDto}
     */
    @PostMapping
    public EventFullDto createEvent(
            @PathVariable long userId,
            @RequestBody NewEventDto newEventDto
    ) {
        log.info("UsersEventsController POST createEvent, got userID: {}, NewEventDto: {}", userId, newEventDto);

        return usersService.createEvent(userId, newEventDto);
    }

    /**
     * Получение полной информации о событии добавленном текущим пользователем
     * @param userId id текущего пользователя
     * @param eventId id события
     * @return {@link EventFullDto}
     */
    @GetMapping("/{eventId}")
    public EventFullDto getById(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        return usersService.getById(userId, eventId);
    }

    /**
     * Отмена события добавленного текущим пользователем.
     * @param userId id текущего пользователя
     * @param eventId id события
     * @return {@link EventFullDto}
     */
    @PatchMapping("/{eventId}")
    public EventFullDto cancelEvent(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        return usersService.cancelEvent(userId, eventId);
    }

    /**
     * Получение информации о запросах на участие в событии текущего пользователя
     * @param userId id текущего пользователя
     * @param eventId id события
     * @return List of {@link ParticipationRequestDto}
     */
    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getRequests(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        return usersService.getRequests(userId, eventId);
    }

    /**
     * Подтверждение чужой заявки на участие в событии текущего пользователя
     * @param userId id текущего пользователя
     * @param eventId id события
     * @param reqId id заявки, которую подтверждает текущий пользователь
     * @return {@link ParticipationRequestDto}
     */
    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto confirmRequest(
            @PathVariable long userId,
            @PathVariable long eventId,
            @PathVariable long reqId
    ) {
        return usersService.confirmRequest(userId, eventId, reqId);
    }

    /**
     * Отклонение чужой заявки на участие в событии текущего пользователя
     * @param userId id текущего пользователя
     * @param eventId id события
     * @param reqId id заявки, которую отменяет текущий пользователь
     * @return {@link ParticipationRequestDto}
     */
    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectRequest(
            @PathVariable long userId,
            @PathVariable long eventId,
            @PathVariable long reqId
    ) {
        return usersService.rejectRequest(userId, eventId, reqId);
    }
}
