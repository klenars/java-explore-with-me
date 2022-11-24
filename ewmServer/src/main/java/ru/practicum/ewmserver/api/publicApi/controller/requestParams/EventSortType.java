package ru.practicum.ewmserver.api.publicApi.controller.requestParams;

/**
 * Enum типов сортировки событий
 */
public enum EventSortType {
    /**Сортировка по дате*/
    EVENT_DATE,
    /**Сортировка по колличеству просмотров*/
    VIEWS,
    /**Сортировка по рейтингу инициатора события*/
    USER_RATING
}
