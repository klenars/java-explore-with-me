package ru.practicum.ewmserver.dto.feedback;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.entity.FeedbackStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Класс ДТО отзыва для отдачи наружу {@link ru.practicum.ewmserver.entity.Feedback},
 * {@link FeedbackDtoOut#id},
 * {@link FeedbackDtoOut#text},
 * {@link FeedbackDtoOut#score},
 * {@link FeedbackDtoOut#eventId},
 * {@link FeedbackDtoOut#userId},
 * {@link FeedbackDtoOut#created},
 * {@link FeedbackDtoOut#status},
 */
@Getter
@Setter
public class FeedbackDtoOut implements Serializable {
    /**Идентификатор*/
    private Long id;
    /**Текст отзыва*/
    private String text;
    /**Оценка событию*/
    private Integer score;
    /**id события*/
    private Long eventId;
    /**id юзера написавшего отзыв*/
    private Long userId;
    /**Дата создания отзыва*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    /**Статус модерации отзыва*/
    private FeedbackStatus status;
}