package ru.practicum.ewmserver.dto.feedback;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.entity.Feedback;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link Feedback} entity
 */
@Getter
@Setter
public class FeedbackDtoIn implements Serializable {

    @Size(min = 5, max = 10000)
    private String text;

    private Integer score;

    private LocalDateTime created;

    public FeedbackDtoIn() {
        this.created = LocalDateTime.now();
    }
}