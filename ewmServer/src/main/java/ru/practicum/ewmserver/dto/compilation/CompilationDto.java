package ru.practicum.ewmserver.dto.compilation;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.dto.event.EventShortDto;

import java.util.List;

/**
 * Кдасс ДТО Подборка событий, имеет поля:
 * {@link CompilationDto#id},
 * {@link CompilationDto#title},
 * {@link CompilationDto#pinned},
 * {@link CompilationDto#events},
 */
@Getter
@Setter
public class CompilationDto {
    /**Идентификатор*/
    private long id;
    /**Заголовок подборки*/
    private String title;
    /**Закреплена ли подборка на главной странице сайта*/
    private boolean pinned;
    /**Список событий входящих в подборку*/
    private List<EventShortDto> events;
}
