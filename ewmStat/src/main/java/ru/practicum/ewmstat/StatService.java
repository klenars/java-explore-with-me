package ru.practicum.ewmstat;

import java.util.List;

public interface StatService {

    void saveHit(EndpointHit hit);

    List<ViewStats> getStats(StatsRequestParams params);
}
