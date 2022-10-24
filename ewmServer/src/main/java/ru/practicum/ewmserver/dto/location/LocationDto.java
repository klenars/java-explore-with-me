package ru.practicum.ewmserver.dto.location;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Класс Дто Локация, содержит поля:
 * {@link LocationDto#lat},
 * {@link LocationDto#lon},
 */
@Getter
@Setter
@ToString
public class LocationDto {

    /**Широта*/
    @NotNull
    private Double lat;

    /**Долгота*/
    @NotNull
    private Double lon;
}
