package ru.practicum.ewmserver.entity;

/**
 * Список статусов модерации отзыва {@link Feedback} на событие
 */
public enum FeedbackStatus {

    /**На модерации, статус по умолчанию для всех новых отзывов*/
    ON_MODERATION,

    /**Прошел модерацию*/
    MODERATED,

    /**Отклонен*/
    REJECTED
}