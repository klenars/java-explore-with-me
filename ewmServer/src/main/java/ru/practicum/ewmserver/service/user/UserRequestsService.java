package ru.practicum.ewmserver.service.user;

import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;

import java.util.List;

/**
 * Интерфейс сервиса обработки запросов по участию в событиях от пользователя
 */
public interface UserRequestsService {

    /**
     * Создать запрос на участие в событии
     * @param userId id юзера
     * @param eventId id события
     * @return {@link ParticipationRequestDto}
     */
    ParticipationRequestDto createRequest(long userId, long eventId);

    /**
     * Отменить запрос на участие в событии
     * @param userId id юзера
     * @param requestId id события
     * @return {@link ParticipationRequestDto}
     */
    ParticipationRequestDto cancelRequest(long userId, long requestId);

    /**
     * Получитьсписок всех запросов юзера
     * @param userId id юзера
     * @return List of {@link ParticipationRequestDto}
     */
    List<ParticipationRequestDto> getAll(long userId);
}
