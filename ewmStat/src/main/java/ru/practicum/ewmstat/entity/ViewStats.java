package ru.practicum.ewmstat.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс для выдачи данных статистики по конкретному url приложения,
 * содержит поля:
 * {@link ViewStats#app},
 * {@link ViewStats#uri},
 * {@link ViewStats#hits}
 */
@Getter
@Setter
public class ViewStats {
    /**Поле указатель на приложение*/
    private String app;

    /**Поле ссылки по которой формируется статистика*/
    private String uri;

    /**Колличество обращений по ссылке*/
    private Long hits;
}
