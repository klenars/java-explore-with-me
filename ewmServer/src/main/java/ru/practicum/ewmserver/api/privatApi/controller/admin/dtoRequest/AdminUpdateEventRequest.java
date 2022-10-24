package ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.ewmserver.dto.location.LocationDto;
import ru.practicum.ewmserver.entity.Event;

/**
 * Класс для обновления {@link Event} от админа, содержит поля:
 * {@link AdminUpdateEventRequest#annotation},
 * {@link AdminUpdateEventRequest#category},
 * {@link AdminUpdateEventRequest#description},
 * {@link AdminUpdateEventRequest#eventDate},
 * {@link AdminUpdateEventRequest#location},
 * {@link AdminUpdateEventRequest#paid},
 * {@link AdminUpdateEventRequest#participantLimit},
 * {@link AdminUpdateEventRequest#requestModeration},
 * {@link AdminUpdateEventRequest#title}
 */
@Getter
@Setter
@ToString
public class AdminUpdateEventRequest {
    /**Краткое описание события*/
    private String annotation;
    /**Категория события*/
    private Long category;
    /**Полное описание события*/
    private String description;
    /**Дата события*/
    private String eventDate;
    /**Место события*/
    private LocationDto location;
    /**Требуется ли оплата*/
    private Boolean paid;
    /**Лимит участников 0 - лимита нет*/
    private Integer participantLimit;
    /**Есть ли модерация заявок на участие*/
    private Boolean requestModeration;
    /**Заголовок события*/
    private String title;
}
