package ru.practicum.ewmserver.service.admin;

import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;

import java.util.List;

/**
 * Интерфейс сервиса по обработке запросов админа по отзывам
 */
public interface AdminFeedbacksService {

    /**
     * Получить все новые отзывы
     * @return List of {@link FeedbackDtoOut}
     */
    List<FeedbackDtoOut> getAllNewFeedbacks();

    /**
     * Отклонить отзыв
     * @param feedId id отзыва
     */
    void rejectFeedback(long feedId);

    /**
     * Подтвердить отзыв
     * @param feedId id отзыва
     * @return {@link FeedbackDtoOut}
     */
    FeedbackDtoOut approveFeedback(long feedId);
}
