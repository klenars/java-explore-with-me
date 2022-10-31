package ru.practicum.ewmserver.service.user;

import ru.practicum.ewmserver.dto.feedback.FeedbackDtoIn;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;

/**
 * Интерфейс сервиса обработки запросов по отзывам от Пользователей {@link ru.practicum.ewmserver.entity.User}
 */
public interface UserFeedbackService {

    /**
     * Добавление нового отзыва на прошедшее событие от юзера который в нем участвовал,
     * пересчитываются рейтинги события и юзера-инициатора
     * @param userId id юзера
     * @param eventId id события
     * @param feedback {@link FeedbackDtoIn}
     * @return {@link FeedbackDtoOut}
     */
    FeedbackDtoOut add(long userId, long eventId, FeedbackDtoIn feedback);

    /**
     * Удаления юзером своего отзыва, пересчитываются рейтинги события и юзера-инициатора
     * @param userId id юзера
     * @param feedId id отзыва
     */
    void delete(long userId, long feedId);

    /**
     * Получение юзером своего отзыва по id
     * @param userId id юзера
     * @param feedId id отзыва
     * @return {@link FeedbackDtoOut}
     */
    FeedbackDtoOut getById(long userId, long feedId);
}
