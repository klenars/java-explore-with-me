package ru.practicum.ewmserver.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.dto.user.UserShortDto;

import java.time.LocalDateTime;

/**
 * Класс ДТО Краткая информация о событии, содержит поля:
 * {@link EventShortDto#id},
 * {@link EventShortDto#category},
 * {@link EventShortDto#title},
 * {@link EventShortDto#annotation},
 * {@link EventShortDto#eventDate},
 * {@link EventShortDto#initiator},
 * {@link EventShortDto#paid},
 * {@link EventShortDto#confirmedRequests},
 * {@link EventShortDto#views},
 */
@Getter
@Setter
public class EventShortDto {
    /**Идентификатор*/
    private Long id;
    /**Категория*/
    private CategoryDto category;
    /**Заголовок*/
    private String title;
    /**Краткое описание*/
    private String annotation;
    /**Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    /**Пользователь (краткая информация)*/
    private UserShortDto initiator;
    /**Нужно ли оплачивать участие*/
    private boolean paid;
    /**Количество одобренных заявок на участие в данном событии*/
    private int confirmedRequests;

    /**Количество просмотрев события*/
    private long views;
}
