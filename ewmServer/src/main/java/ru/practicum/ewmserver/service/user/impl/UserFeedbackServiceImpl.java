package ru.practicum.ewmserver.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoIn;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;
import ru.practicum.ewmserver.entity.*;
import ru.practicum.ewmserver.error.ForbiddenError;
import ru.practicum.ewmserver.mapper.FeedbackMapper;
import ru.practicum.ewmserver.repository.EventRepository;
import ru.practicum.ewmserver.repository.FeedbackRepository;
import ru.practicum.ewmserver.repository.RequestRepository;
import ru.practicum.ewmserver.repository.UserRepository;
import ru.practicum.ewmserver.service.user.UserFeedbackService;

/**
 * Реализация интерфейса {@link UserFeedbackService}, имеет поля:
 * {@link UserFeedbackServiceImpl#feedbackRepository},
 * {@link UserFeedbackServiceImpl#userRepository},
 * {@link UserFeedbackServiceImpl#eventRepository},
 * {@link UserFeedbackServiceImpl#requestRepository},
 * {@link UserFeedbackServiceImpl#feedbackMapper},
 */
@Service
@RequiredArgsConstructor
public class UserFeedbackServiceImpl implements UserFeedbackService {

    /**Репозиторий отзывов*/
    private final FeedbackRepository feedbackRepository;
    /**Репозиторий юзеров*/
    private final UserRepository userRepository;
    /**Репозиторий событий*/
    private final EventRepository eventRepository;
    /**Репозиторий запросов на участие*/
    private final RequestRepository requestRepository;
    /**Маппер отзывов*/
    private final FeedbackMapper feedbackMapper;

    @Override
    @Transactional
    public FeedbackDtoOut add(long userId, long eventId, FeedbackDtoIn feedbackDtoIn) {
        User user = userRepository.getById(userId);
        Event event = eventRepository.getById(eventId);
        checkDataForFeedback(user, event, feedbackDtoIn);

        Feedback feedback = feedbackRepository.save(feedbackMapper.dtoInToEntity(feedbackDtoIn, event, user));
        updateEventRating(event);
        updateUserRating(event.getInitiator());

        event.getFeedbacks().add(feedback);

        return feedbackMapper.toDtoOut(feedback);
    }

    @Override
    @Transactional
    public void delete(long userId, long feedId) {
        User user = userRepository.getById(userId);
        Feedback feedback = feedbackRepository.getById(feedId);
        checkFeedbackAuthor(feedback, user);
        Event event = feedback.getEvent();

        event.getFeedbacks().remove(feedback);
        feedbackRepository.delete(feedback);

        updateEventRating(event);
        updateUserRating(event.getInitiator());
    }

    @Override
    public FeedbackDtoOut getById(long userId, long feedId) {
        User user = userRepository.getById(userId);
        Feedback feedback = feedbackRepository.getById(feedId);
        checkFeedbackAuthor(feedback, user);

        return feedbackMapper.toDtoOut(feedbackRepository.getById(feedId));
    }

    /**
     * Проверка что юзер является автором отзыва
     * @param feedback {@link Feedback}
     * @param user {@link User}
     */
    private void checkFeedbackAuthor(Feedback feedback, User user) {
        if (!feedback.getUser().equals(user)) {
            throw new ForbiddenError(
                    String.format("Пользователь id=%d не является автором отзыва id=%d", user.getId(), feedback.getId())
            );
        }
    }

    /**
     * Обновление рейтинга инициатора события
     * @param initiator {@link User}
     */
    private void updateUserRating(User initiator) {
        initiator.setRating(eventRepository.avgRatingByInitiatorId(initiator.getId()).orElse(0.0));
    }

    /**
     * Обновление рейтинга события
     * @param event {@link Event}
     */
    private void updateEventRating(Event event) {
        event.setRating(feedbackRepository.avgScoreByEventId(event.getId()).orElse(0.0));
    }

    /**
     * Проверки данных для создания нового отзыва:
     * 1. Событие должно уже произойти (дата события в прошлом и статус PUBLISHED).
     * 2. Отзыв оставляет не инициатор события.
     * 3. Пользователь может оставить только 1 отзыв.
     * 4. Пользователь участвовал в событии (есть подтвержденная от него заявка на событие).
     * @param user {@link User}
     * @param event {@link Event}
     * @param feedbackDtoIn {@link FeedbackDtoIn}
     */
    private void checkDataForFeedback(User user, Event event, FeedbackDtoIn feedbackDtoIn) {
        if (event.getEventDate().isAfter(feedbackDtoIn.getCreated())) {
            throw new ForbiddenError("Нельзя оставить отзыв на событие которое еще не произошло.");
        }

        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ForbiddenError(
                    String.format("Событии id=%d было отклонено, отзыв оставить нельзя", event.getId())
            );
        }

        if (event.getInitiator().equals(user)) {
            throw new ForbiddenError(
                    String.format(
                            "Пользователь id=%d инициатор события id=%d, на свое событие отзыв оставить нельзя",
                            user.getId(),
                            event.getId()
                    )
            );
        }

        if (feedbackRepository.existsByEventIdAndUserId(event.getId(), user.getId())) {
            throw new ForbiddenError(
                    String.format("Пользователь id=%d уже оставил отзыв на событие id=%d", user.getId(), event.getId())
            );
        }

        if (!requestRepository.existsByEventIdAndRequesterIdAndStatus(
                event.getId(),
                user.getId(),
                RequestStatus.CONFIRMED)
        ) {
            throw new ForbiddenError(
                    String.format("Пользователь id=%d не участвовал в событии id=%d", user.getId(), event.getId())
            );
        }
    }
}
