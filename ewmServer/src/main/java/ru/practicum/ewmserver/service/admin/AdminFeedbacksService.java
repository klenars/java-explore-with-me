package ru.practicum.ewmserver.service.admin;

import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;

import java.util.List;

public interface AdminFeedbacksService {

    List<FeedbackDtoOut> getAllNewFeedbacks();

    void rejectFeedback(long feedId);

    FeedbackDtoOut approveFeedback(long feedId);
}
