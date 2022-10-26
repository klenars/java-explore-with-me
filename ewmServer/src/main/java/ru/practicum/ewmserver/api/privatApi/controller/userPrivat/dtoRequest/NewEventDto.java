package ru.practicum.ewmserver.api.privatApi.controller.userPrivat.dtoRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.ewmserver.dto.location.LocationDto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Класс Нового события, содержит поля:
 * {@link NewEventDto#annotation} @NotBlank Size(min = 20, max = 2000),
 * {@link NewEventDto#category} NotNull,
 * {@link NewEventDto#description}@NotBlank @Size(min = 20, max = 7000),
 * {@link NewEventDto#eventDate} @Future,
 * {@link NewEventDto#location} @NotNull,
 * {@link NewEventDto#paid},
 * {@link NewEventDto#participantLimit},
 * {@link NewEventDto#requestModeration},
 * {@link NewEventDto#title} @NotBlank @Size(min = 3, max = 120),
 */
@Getter
@Setter
@ToString
public class NewEventDto {

    /**Краткое описание события*/
    @NotBlank
    @Size(min = 20, max = 2000)
    private String annotation;

    /**id категории к которой относится событие*/
    @NotNull
    private Long category;

    /**Полное описание события*/
    @NotBlank
    @Size(min = 20, max = 7000)
    private String description;

    /**Дата и время на которые намечено событие.*/
    @Future
    private String eventDate;

    /**
     Широта и долгота места проведения события*/
    @NotNull
    private LocationDto location;

    /**Нужно ли оплачивать участие в событии*/
    private boolean paid;

    /**Ограничение на количество участников.*/
    private int participantLimit;

    /**Нужна ли пре-модерация заявок на участие.*/
    private boolean requestModeration;

    /**Заголовок события*/
    @NotBlank
    @Size(min = 3, max = 120)
    private String title;

    public NewEventDto() {
        this.requestModeration = true;
    }
}
