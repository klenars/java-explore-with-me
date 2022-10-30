package ru.practicum.ewmserver.dto.feedback;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link ru.practicum.ewmserver.entity.Feedback} entity
 */
@Getter
@Setter
public class FeedbackDtoOut implements Serializable {
    private Long id;
    @Size(min = 5, max = 10000)
    private String text;
    private Integer score;
    private Long eventId;
    private Long userId;
}