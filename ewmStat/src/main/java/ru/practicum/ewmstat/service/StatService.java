package ru.practicum.ewmstat.service;

import ru.practicum.ewmstat.entity.EndpointHit;
import ru.practicum.ewmstat.specification.StatsRequestParams;
import ru.practicum.ewmstat.entity.ViewStats;

import java.util.List;

/**
 * Интерфейс сервиса обработки, сохранения и выдаче данных статистики обращения к эндпоинтам
 */
public interface StatService {

    /**
     * Метод сохранения данных
     * @param hit экземляр класса {@link EndpointHit}
     */
    void saveHit(EndpointHit hit);

    /**
     * Метод получения данных
     * @param params экземпляр класса {@link StatsRequestParams}
     * @return List экземляров класса {@link ViewStats}
     */
    List<ViewStats> getStats(StatsRequestParams params);
}
