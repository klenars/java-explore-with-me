package ru.practicum.ewmserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Класс Entity Отзыв, содержит поля:
 * {@link Feedback#id},
 * {@link Feedback#text},
 * {@link Feedback#score},
 * {@link Feedback#event},
 * {@link Feedback#user},
 * {@link Feedback#created},
 * {@link Feedback#status},
 */
@Entity
@Getter
@Setter
public class Feedback {

    /**Идентификатор*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**Текст отзыва*/
    @Size(min = 5, max = 10000)
    private String text;
    /**Оценка событию*/
    private Integer score;
    /**Событие*/
    @ManyToOne
    private Event event;
    /**Автор отзыва*/
    @OneToOne
    private User user;
    /**Дата создания отзыва*/
    private LocalDateTime created;
    /**Статус модерации отзыва*/
    @Enumerated(EnumType.STRING)
    private FeedbackStatus status;

    public Feedback() {
        status = FeedbackStatus.ON_MODERATION;
    }
}
