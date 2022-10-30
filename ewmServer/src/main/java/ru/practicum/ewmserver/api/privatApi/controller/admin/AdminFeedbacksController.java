package ru.practicum.ewmserver.api.privatApi.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.feedback.FeedbackDtoOut;
import ru.practicum.ewmserver.service.admin.AdminFeedbacksService;

import java.util.List;

@RestController
@RequestMapping("/admin/feedbacks")
@RequiredArgsConstructor
public class AdminFeedbacksController {

    private final AdminFeedbacksService service;

    @GetMapping
    public List<FeedbackDtoOut> getAllNewFeedbacks() {
        return service.getAllNewFeedbacks();
    }

    @PatchMapping("/{feedId}/reject")
    public void rejectFeedback(@PathVariable long feedId) {
        service.rejectFeedback(feedId);
    }

    @PatchMapping("/{feedId}approve")
    public FeedbackDtoOut approveFeedback(@PathVariable long feedId) {
        return service.approveFeedback(feedId);
    }
}
