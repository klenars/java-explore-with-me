package ru.practicum.ewmserver.dto.location;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LocationDto {

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;
}
