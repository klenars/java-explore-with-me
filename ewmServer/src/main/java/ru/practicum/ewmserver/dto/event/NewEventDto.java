package ru.practicum.ewmserver.dto.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.ewmserver.dto.location.LocationDto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class NewEventDto {

    @NotBlank
    @Size(min = 20, max = 2000)
    private String annotation;

    @NotNull
    private Long category;

    @NotBlank
    @Size(min = 20, max = 7000)
    private String description;

    @Future
    private String eventDate;

    @NotNull
    private LocationDto location;

    private boolean paid;

    private int participantLimit;

    private boolean requestModeration;

    @NotBlank
    @Size(min = 3, max = 120)
    private String title;

    public NewEventDto() {
        this.requestModeration = true;
    }
}
