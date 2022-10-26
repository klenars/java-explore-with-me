package ru.practicum.ewmserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Базовый класс Entity Подборка, содержит поля:
 * {@link Compilation#id},
 * {@link Compilation#title},
 * {@link Compilation#pinned},
 * {@link Compilation#events},
 */
@Entity
@Getter
@Setter
public class Compilation {

    /**Идентификатор*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**Описание подборки*/
    private String title;

    /**Закреплена ли подборка на главной странице сайта*/
    private boolean pinned;

    /**Список событий входящих в подборку*/
    @ManyToMany
    private List<Event> events;
}
