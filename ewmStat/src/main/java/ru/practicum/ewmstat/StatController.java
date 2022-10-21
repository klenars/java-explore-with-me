package ru.practicum.ewmstat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatController {

    private final StatService statService;

    @PostMapping("/hit")
    public void saveHit(@RequestBody EndpointHit hit) {
        statService.saveHit(hit);
    }

    @GetMapping("/stats")
    public List<ViewStats> getStats(StatsRequestParams params) {
        return statService.getStats(params);
    }
}
