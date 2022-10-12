package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewmserver.dto.location.LocationDto;
import ru.practicum.ewmserver.entity.Location;

@Mapper
public interface LocationMapper {

    LocationDto toDto(Location location);

    Location toEntity(LocationDto locationDto);
}
