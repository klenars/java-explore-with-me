package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.practicum.ewmserver.dto.location.LocationDto;
import ru.practicum.ewmserver.entity.Location;

@Mapper
public interface LocationMapper {

    @Named("toDto")
    LocationDto toDto(Location location);

    @Named("toEntity")
    Location toEntity(LocationDto locationDto);
}
