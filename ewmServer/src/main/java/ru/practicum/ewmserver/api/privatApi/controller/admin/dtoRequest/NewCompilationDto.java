package ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;
import ru.practicum.ewmserver.entity.Event;

/**
 * Класс создания новой подборки событий {@link Event}, сожержит поля:
 * {@link NewCompilationDto#title},
 * {@link NewCompilationDto#events},
 * {@link NewCompilationDto#pinned}
 */
@Getter
@Setter
@ToString
public class NewCompilationDto {

    /**Заголовок подборки*/
    @NotNull
    private String title;

    /**Список идентификаторов событий входящих в подборку*/
    private List<Long> events;

    /**Закреплена ли подборка на главной странице сайта*/
    private boolean pinned;
}
