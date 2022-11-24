package ru.practicum.ewmserver.mapper;

import org.mapstruct.*;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;
import ru.practicum.ewmserver.entity.Event;
import ru.practicum.ewmserver.entity.Feedback;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoIn;
import ru.practicum.ewmserver.entity.User;

/**
 * Маппер отзывов
 */
@Mapper
public interface FeedbackMapper {

    /**
     * Маппер входящего ДТО в Сущность
     * @param feedbackDtoIn {@link FeedbackDtoIn}
     * @param event {@link Event}
     * @param user {@link User}
     * @return {@link Feedback}
     */
    @Mapping(target = "event", expression = "java(event)")
    @Mapping(target = "user", expression = "java(user)")
    @Mapping(target = "id", ignore = true)
    Feedback dtoInToEntity(FeedbackDtoIn feedbackDtoIn, Event event, User user);

    /**
     * Маппинг Сущности отзыва в исходящее ДТО
     * @param feedback {@link Feedback}
     * @return {@link FeedbackDtoOut}
     */
    @Named("toDtoOut")
    @Mapping(target = "eventId", source = "event.id")
    @Mapping(target = "userId", source = "user.id")
    FeedbackDtoOut toDtoOut(Feedback feedback);
}
