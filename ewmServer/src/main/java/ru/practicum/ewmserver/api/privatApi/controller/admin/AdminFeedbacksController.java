package ru.practicum.ewmserver.api.privatApi.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;
import ru.practicum.ewmserver.service.admin.AdminFeedbacksService;

import java.util.List;

/**
 * Контроллер по обработке запросов от админа по отзывам на события, имеет поле:
 * {@link AdminFeedbacksController#service}
 */
@RestController
@RequestMapping("/admin/feedbacks")
@RequiredArgsConstructor
public class AdminFeedbacksController {

    /**Интерфейс сервиса обработки запросов от админа по отзывам*/
    private final AdminFeedbacksService service;

    /**
     * Получить все отзывы не прошедшие модерацию
     * @return List of {@link FeedbackDtoOut}
     */
    @GetMapping
    public List<FeedbackDtoOut> getAllNewFeedbacks() {
        return service.getAllNewFeedbacks();
    }

    /**
     * Отклонить отзыв
     * @param feedId id отзыва
     */
    @PatchMapping("/{feedId}/reject")
    public void rejectFeedback(@PathVariable long feedId) {
        service.rejectFeedback(feedId);
    }

    /**
     * Подтвердить отзыв
     * @param feedId id отзыва
     * @return {@link FeedbackDtoOut}
     */
    @PatchMapping("/{feedId}/approve")
    public FeedbackDtoOut approveFeedback(@PathVariable long feedId) {
        return service.approveFeedback(feedId);
    }
}
