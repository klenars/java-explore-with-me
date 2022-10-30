package ru.practicum.ewmserver.mapper;

import org.mapstruct.*;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;
import ru.practicum.ewmserver.entity.Event;
import ru.practicum.ewmserver.entity.Feedback;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoIn;
import ru.practicum.ewmserver.entity.User;

@Mapper
public interface FeedbackMapper {

    @Mapping(target = "event", expression = "java(event)")
    @Mapping(target = "user", expression = "java(user)")
    @Mapping(target = "id", ignore = true)
    Feedback dtoInToEntity(FeedbackDtoIn feedbackDtoIn, Event event, User user);

    @Mapping(target = "eventId", source = "event.id")
    @Mapping(target = "userId", source = "user.id")
    FeedbackDtoOut toDtoOut(Feedback feedback);
}
