package ru.practicum.ewmserver.service.user;

import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.api.privatApi.controller.userPrivat.dtoRequest.NewEventDto;
import ru.practicum.ewmserver.api.privatApi.controller.userPrivat.dtoRequest.UpdateEventRequest;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;

import java.util.List;

/**
 * Интерфейс Сервиса обработки запросов по событиям от пользователя
 */
public interface UsersService {

    /**
     * Получить список событий
     * @param userId id юзера
     * @param from номер первого элемента
     * @param size размер страницы
     * @return List of {@link EventShortDto}
     */
    List<EventShortDto> getAll(long userId, int from, int size);

    /**
     * Создать событие
     * @param userId id юзера
     * @param newEventDto {@link NewEventDto}
     * @return {@link EventFullDto}
     */
    EventFullDto createEvent(long userId, NewEventDto newEventDto);

    /**
     * Обновить событие
     * @param userId id юзера
     * @param updateEventRequest {@link UpdateEventRequest}
     * @return {@link EventFullDto}
     */
    EventFullDto updateEvent(long userId, UpdateEventRequest updateEventRequest);

    /**
     * Получить событие по id
     * @param userId id юзера
     * @param eventId id события
     * @return {@link EventFullDto}
     */
    EventFullDto getById(long userId, long eventId);

    /**
     * Отменить событие
     * @param userId id юзера
     * @param eventId id события
     * @return {@link EventFullDto}
     */
    EventFullDto cancelEvent(long userId, long eventId);

    /**
     * Получить список запросов на участие в событии
     * @param userId id юзера
     * @param eventId id события
     * @return List of {@link ParticipationRequestDto}
     */
    List<ParticipationRequestDto> getRequests(long userId, long eventId);

    /**
     * Подтвердить запрос на участие в событии
     * @param userId id юзера
     * @param eventId id события
     * @param reqId id запроса
     * @return {@link ParticipationRequestDto}
     */
    ParticipationRequestDto confirmRequest(long userId, long eventId, long reqId);

    /**
     * Отклонить запрос на участие в событии
     * @param userId id юзера
     * @param eventId id события
     * @param reqId id запроса
     * @return {@link ParticipationRequestDto}
     */
    ParticipationRequestDto rejectRequest(long userId, long eventId, long reqId);
}
