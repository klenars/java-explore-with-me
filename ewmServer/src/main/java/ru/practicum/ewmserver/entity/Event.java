package ru.practicum.ewmserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс Entity Событие, содержит поля:
 * {@link Event#id},
 * {@link Event#category},
 * {@link Event#title},
 * {@link Event#annotation},
 * {@link Event#eventDate},
 * {@link Event#initiator},
 * {@link Event#location},
 * {@link Event#paid},
 * {@link Event#confirmedRequests},
 * {@link Event#createdOn},
 * {@link Event#description},
 * {@link Event#participantLimit},
 * {@link Event#publishedOn},
 * {@link Event#requestModeration},
 * {@link Event#state},
 * {@link Event#views},
 */
@Entity(name = "events")
@Getter
@Setter
@ToString
public class Event {

    /**Идентификатор*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**Категория*/
    @ManyToOne
    private Category category;
    /**Заголовок*/
    @Column(length = 120)
    private String title;
    /**Краткое описание*/
    @Column(length = 2000)
    private String annotation;
    /**Дата и время на которые намечено событие*/
    private LocalDateTime eventDate;
    /**Пользователь*/
    @ManyToOne
    private User initiator;
    /**Широта и долгота места проведения события*/
    @OneToOne
    private Location location;
    /**Нужно ли оплачивать участие*/
    private boolean paid;
    /**Количество одобренных заявок на участие в данном событии*/
    private int confirmedRequests;
    /**Дата и время создания события*/
    private LocalDateTime createdOn;
    /**Полное описание события*/
    @Column(length = 7000)
    private String description;
    /**Ограничение на количество участников. Значение 0 - означает отсутствие ограничения*/
    private int participantLimit;
    /**Дата и время публикации события*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;
    /**Нужна ли пре-модерация заявок на участие*/
    private boolean requestModeration;
    /**Список состояний жизненного цикла события*/
    @Enumerated(EnumType.STRING)
    private EventState state;
    /**Количество просмотрев события*/
    private long views;

    /**Рейтинг события*/
    private Double rating;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;

    public Event() {
        this.createdOn = LocalDateTime.now();
        this.state = EventState.PENDING;
        this.feedbacks = new ArrayList<>();
    }
}
