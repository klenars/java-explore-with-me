package ru.practicum.ewmserver.api.privatApi.controller.userPrivat.dtoRequest;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.entity.Event;

import javax.validation.constraints.NotNull;

/**
 * Класс Данных для изменения информации о событии {@link Event}, содержит поля:
 * {@link UpdateEventRequest#annotation},
 * {@link UpdateEventRequest#category},
 * {@link UpdateEventRequest#description},
 * {@link UpdateEventRequest#eventDate},
 * {@link UpdateEventRequest#eventId},
 * {@link UpdateEventRequest#paid},
 * {@link UpdateEventRequest#participantLimit},
 * {@link UpdateEventRequest#title},
 */
@Getter
@Setter
public class UpdateEventRequest {

    /**Краткое описание события*/
    private String annotation;

    /**id категории к которой относится событие*/
    private Long category;

    /**Полное описание события*/
    private String description;

    /**Дата и время на которые намечено событие.*/
    private String eventDate;

    /**id события*/
    @NotNull
    private Long eventId;

    /**Нужно ли оплачивать участие в событии*/
    private Boolean paid;

    /**Ограничение на количество участников.*/
    private Integer participantLimit;

    /**Заголовок события*/
    private String title;
}
