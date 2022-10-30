package ru.practicum.ewmserver.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;
import ru.practicum.ewmserver.entity.Event;
import ru.practicum.ewmserver.entity.Feedback;
import ru.practicum.ewmserver.entity.FeedbackStatus;
import ru.practicum.ewmserver.entity.User;
import ru.practicum.ewmserver.mapper.FeedbackMapper;
import ru.practicum.ewmserver.repository.EventRepository;
import ru.practicum.ewmserver.repository.FeedbackRepository;
import ru.practicum.ewmserver.service.admin.AdminFeedbacksService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminFeedbacksServiceImpl implements AdminFeedbacksService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final EventRepository eventRepository;

    @Override
    public List<FeedbackDtoOut> getAllNewFeedbacks() {
        return feedbackRepository.findByStatus(FeedbackStatus.ON_MODERATION).stream()
                .map(feedbackMapper::toDtoOut)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void rejectFeedback(long feedId) {
        Feedback feedback = feedbackRepository.getById(feedId);
        feedback.setStatus(FeedbackStatus.REJECTED);
        Event event = eventRepository.getById(feedback.getEvent().getId());
        updateEventRating(event);
        updateUserRating(event.getInitiator());
    }

    @Override
    @Transactional
    public FeedbackDtoOut approveFeedback(long feedId) {
        Feedback feedback = feedbackRepository.getById(feedId);
        feedback.setStatus(FeedbackStatus.MODERATED);

        return feedbackMapper.toDtoOut(feedback);
    }

    private void updateUserRating(User initiator) {
        initiator.setRating(eventRepository.avgRatingByInitiatorId(initiator.getId()));
    }

    private void updateEventRating(Event event) {
        event.setRating(feedbackRepository.avgScoreByEventId(event.getId()));
    }
}
