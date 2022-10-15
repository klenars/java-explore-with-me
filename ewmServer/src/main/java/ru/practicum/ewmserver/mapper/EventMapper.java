package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.NewEventDto;
import ru.practicum.ewmserver.entity.Event;

import java.time.LocalDateTime;

@Mapper(uses = {CategoryMapper.class, LocationMapper.class, UserMapper.class})
public interface EventMapper {

    @Mapping(target = "category", qualifiedByName = "toDto")
    @Mapping(target = "initiator", qualifiedByName = "toShortDto")
    @Mapping(target = "location", qualifiedByName = "toDto")
    EventFullDto toFullDto(Event event);

    @Named("toShortDto")
    @Mapping(target = "category", qualifiedByName = "toDto")
    @Mapping(target = "initiator", qualifiedByName = "toShortDto")
    EventShortDto toShortDto(Event event);

    @Mapping(target = "eventDate", expression = "java( parseDate(newEventDto.getEventDate()) )")
    @Mapping(target = "location", qualifiedByName = "toEntity")
    @Mapping(target = "category", ignore = true)
    Event toEntity(NewEventDto newEventDto);

    default LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date);
    }
}
