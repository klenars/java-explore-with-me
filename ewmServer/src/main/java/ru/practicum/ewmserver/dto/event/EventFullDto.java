package ru.practicum.ewmserver.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;
import ru.practicum.ewmserver.dto.location.LocationDto;
import ru.practicum.ewmserver.dto.user.UserShortDto;
import ru.practicum.ewmserver.entity.EventState;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс ДТО с полной информацией о событии, содержит поля:
 * {@link EventFullDto#id},
 * {@link EventFullDto#category},
 * {@link EventFullDto#title},
 * {@link EventFullDto#annotation},
 * {@link EventFullDto#eventDate},
 * {@link EventFullDto#initiator},
 * {@link EventFullDto#location},
 * {@link EventFullDto#paid},
 * {@link EventFullDto#confirmedRequests},
 * {@link EventFullDto#createdOn},
 * {@link EventFullDto#description},
 * {@link EventFullDto#participantLimit},
 * {@link EventFullDto#publishedOn},
 * {@link EventFullDto#requestModeration},
 * {@link EventFullDto#state},
 * {@link EventFullDto#views},
 */
@Getter
@Setter
public class EventFullDto {
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
    /**Широта и долгота места проведения события*/
    private LocationDto location;
    /**Нужно ли оплачивать участие*/
    private boolean paid;
    /**Количество одобренных заявок на участие в данном событии*/
    private int confirmedRequests;
    /**Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss")*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
    /**Полное описание события*/
    private String description;
    /**Ограничение на количество участников. Значение 0 - означает отсутствие ограничения*/
    private int participantLimit;
    /**Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;
    /**Нужна ли пре-модерация заявок на участие*/
    private boolean requestModeration;
    /**Список состояний жизненного цикла события*/
    private EventState state;
    /**Количество просмотрев события*/
    private long views;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FeedbackDtoOut> feedbacks;
}
