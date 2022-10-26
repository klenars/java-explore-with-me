package ru.practicum.ewmserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Запрос на участие в событии, содержит поля:
 * {@link ParticipationRequest#id},
 * {@link ParticipationRequest#event},
 * {@link ParticipationRequest#created},
 * {@link ParticipationRequest#requester},
 * {@link ParticipationRequest#status},
 */
@Entity(name = "requests")
@Getter
@Setter
public class ParticipationRequest {

    /**Идентификатор*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**Событие*/
    @ManyToOne
    private Event event;

    /**Дата создания запроса*/
    private LocalDateTime created;

    /**Пользователь*/
    @ManyToOne
    private User requester;

    /**Статус запроса*/
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
