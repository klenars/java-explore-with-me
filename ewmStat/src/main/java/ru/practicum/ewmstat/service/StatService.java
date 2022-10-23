package ru.practicum.ewmstat.service;

import ru.practicum.ewmstat.entity.EndpointHit;
import ru.practicum.ewmstat.specification.StatsRequestParams;
import ru.practicum.ewmstat.entity.ViewStats;

import java.util.List;

public interface StatService {

    void saveHit(EndpointHit hit);

    List<ViewStats> getStats(StatsRequestParams params);
}
