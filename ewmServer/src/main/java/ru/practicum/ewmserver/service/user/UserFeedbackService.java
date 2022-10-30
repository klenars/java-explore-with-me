package ru.practicum.ewmserver.service.user;

import ru.practicum.ewmserver.dto.feedback.FeedbackDtoIn;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;

public interface UserFeedbackService {
    FeedbackDtoOut add(long userId, long eventId, FeedbackDtoIn feedback);

    void delete(long userId, long feedId);

    FeedbackDtoOut getById(long userId, long feedId);
}
