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
 * содержит поле класса StatService
 * @see StatService
 */
@RestController
@RequiredArgsConstructor
public class StatController {

    private final StatService statService;

    /**
     * Эндпоинт для сохранения данных статистики
     * @param hit экземпляр класса EndpointHit с данными статистики
     * @see EndpointHit
     */
    @PostMapping("/hit")
    public void saveHit(@RequestBody EndpointHit hit) {
        statService.saveHit(hit);
    }

    /**
     * Эндпоинт для сохранения данных статистики
     * @param params экземпляр класса StatsRequestParams с параметрами фильтрации статистики
     * @see StatsRequestParams
     * @see ViewStats
     * @return List обектов класса ViewStats
     */
    @GetMapping("/stats")
    public List<ViewStats> getStats(StatsRequestParams params) {
        return statService.getStats(params);
    }
}
