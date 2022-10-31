package ru.practicum.ewmserver.dto.feedback;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.entity.Feedback;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Класс входящего ДТО отзыва {@link Feedback}, содержит поля:
 * {@link FeedbackDtoIn#text},
 * {@link FeedbackDtoIn#score},
 * {@link FeedbackDtoIn#created}
 */
@Getter
@Setter
public class FeedbackDtoIn implements Serializable {

    /**Текст отзыва*/
    @Size(min = 5, max = 10000)
    private String text;

    /**Оценка событию*/
    private Integer score;

    /**Дата создания отзыва*/
    private LocalDateTime created;

    /**Конструктор по умолчанию присваивает дате создания текущую дату*/
    public FeedbackDtoIn() {
        this.created = LocalDateTime.now();
    }
}