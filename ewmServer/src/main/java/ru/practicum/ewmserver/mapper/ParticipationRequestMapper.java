package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;
import ru.practicum.ewmserver.entity.ParticipationRequest;

/**
 * Маппер Запросов на участие в событиях
 */
@Mapper
public interface ParticipationRequestMapper {

    /**
     * Маппер ParticipationRequest в ParticipationRequestDto
     * @param participationRequest {@link ParticipationRequest}
     * @return {@link ParticipationRequestDto}
     */
    @Named("toDto")
    @Mapping(target = "event", source = "event.id")
    @Mapping(target = "requester", source = "requester.id")
    ParticipationRequestDto toDto(ParticipationRequest participationRequest);
}
