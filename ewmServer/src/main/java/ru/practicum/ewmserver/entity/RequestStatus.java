package ru.practicum.ewmserver.entity;

/**
 * Список возможных состояний запроса
 */
public enum RequestStatus {
    /**Ожидает модерации*/
    PENDING,
    /**Подтвержден*/
    CONFIRMED,
    /**Завершен*/
    CANCELED,
    /**Отклонен*/
    REJECTED
}
