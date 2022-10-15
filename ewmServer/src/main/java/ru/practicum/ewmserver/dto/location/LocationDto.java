package ru.practicum.ewmserver.dto.location;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class LocationDto {

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;
}
