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

/**
 * Реализация интерфейса {@link AdminFeedbacksService}, имеет поля:
 * {@link AdminFeedbacksServiceImpl#feedbackRepository},
 * {@link AdminFeedbacksServiceImpl#feedbackMapper},
 * {@link AdminFeedbacksServiceImpl#eventRepository}
 */
@Service
@RequiredArgsConstructor
public class AdminFeedbacksServiceImpl implements AdminFeedbacksService {

    /**Репозиторий отзывов*/
    private final FeedbackRepository feedbackRepository;
    /**Маппер отзывов*/
    private final FeedbackMapper feedbackMapper;
    /**Репозиторий событий*/
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

    /**
     * Обновление рейтинг юзера
     * @param initiator {@link User} инициатор события
     */
    private void updateUserRating(User initiator) {
        initiator.setRating(eventRepository.avgRatingByInitiatorId(initiator.getId()));
    }

    /**
     * Обноведние рейтинга события
     * @param event {@link Event}
     */
    private void updateEventRating(Event event) {
        event.setRating(feedbackRepository.avgScoreByEventId(event.getId()));
    }
}
