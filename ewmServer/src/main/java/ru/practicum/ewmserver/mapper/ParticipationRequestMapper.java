package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;
import ru.practicum.ewmserver.entity.ParticipationRequest;

@Mapper
public interface ParticipationRequestMapper {

    @Mapping(target = "event", source = "event.id")
    @Mapping(target = "requester", source = "requester.id")
    ParticipationRequestDto toDto(ParticipationRequest participationRequest);
}
