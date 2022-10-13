package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    @Mapping(target = "category", qualifiedByName = "toDto")
    @Mapping(target = "initiator", qualifiedByName = "toShortDto")
    EventShortDto toShortDto(Event event);

    @Mapping(target = "eventDate", expression = "java( parseDate(eventDate) )")
    @Mapping(target = "location", qualifiedByName = "toEntity")
    Event toEntity(NewEventDto newEventDto);

    private LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date);
    }
}
