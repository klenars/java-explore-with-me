package ru.practicum.ewmserver.service.client;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Класс для выдачи данных статистики по конкретному url приложения,
 * содержит поля:
 * {@link ViewStats#app},
 * {@link ViewStats#uri},
 * {@link ViewStats#hits}
 */
@Getter
@Setter
public class ViewStats implements Serializable {
    /**Поле указатель на приложение*/
    private String app;

    /**Поле ссылки по которой формируется статистика*/
    private String uri;

    /**Колличество обращений по ссылке*/
    private Long hits;
}
