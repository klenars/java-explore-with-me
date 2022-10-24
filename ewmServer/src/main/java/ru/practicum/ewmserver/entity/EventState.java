package ru.practicum.ewmserver.entity;

/**Список состояний жизненного цикла события*/
public enum EventState {
    /**Ожидает модерации*/
    PENDING,
    /**Опубликовано*/
    PUBLISHED,
    /**Отклонено*/
    CANCELED
}
