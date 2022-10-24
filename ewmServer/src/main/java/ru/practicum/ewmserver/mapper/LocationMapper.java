package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.practicum.ewmserver.dto.location.LocationDto;
import ru.practicum.ewmserver.entity.Location;

/**
 * Маппер локации
 */
@Mapper
public interface LocationMapper {

    /**
     * Маппер Location в LocationDto
     * @param location {@link Location}
     * @return {@link LocationDto}
     */
    @Named("toDto")
    LocationDto toDto(Location location);

    /**
     * Маппер LocationDto в Location
     * @param locationDto {@link LocationDto}
     * @return {@link Location}
     */
    @Named("toEntity")
    Location toEntity(LocationDto locationDto);
}
