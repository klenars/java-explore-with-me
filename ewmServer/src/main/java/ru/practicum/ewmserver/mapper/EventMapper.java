package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.api.privatApi.controller.userPrivat.dtoRequest.NewEventDto;
import ru.practicum.ewmserver.entity.Category;
import ru.practicum.ewmserver.entity.Event;
import ru.practicum.ewmserver.entity.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @Mapping(target = "category", expression = "java( category )")
    @Mapping(target = "initiator", expression = "java( initiator )")
    @Mapping(target = "id", ignore = true)
    Event toEntity(NewEventDto newEventDto, Category category, User initiator);

    default LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
