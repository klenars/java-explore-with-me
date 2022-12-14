package ru.practicum.ewmstat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmstat.entity.EndpointHit;
import ru.practicum.ewmstat.service.StatService;
import ru.practicum.ewmstat.specification.StatsRequestParams;
import ru.practicum.ewmstat.entity.ViewStats;

import java.util.List;

/**
 * Контроллер сервиса статистики
 * содержит поле класса {@link StatService}
 */
@RestController
@RequiredArgsConstructor
public class StatController {

    private final StatService statService;

    /**
     * Эндпоинт для сохранения данных статистики
     * @param hit экземпляр класса {@link EndpointHit} с данными статистики
     */
    @PostMapping("/hit")
    public void saveHit(@RequestBody EndpointHit hit) {
        statService.saveHit(hit);
    }

    /**
     * Эндпоинт для сохранения данных статистики
     * @param params экземпляр класса {@link StatsRequestParams} с параметрами фильтрации статистики
     * @return List обектов класса {@link ViewStats}
     */
    @GetMapping("/stats")
    public List<ViewStats> getStats(StatsRequestParams params) {
        return statService.getStats(params);
    }
}
