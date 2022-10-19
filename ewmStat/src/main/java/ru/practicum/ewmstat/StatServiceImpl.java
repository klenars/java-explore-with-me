package ru.practicum.ewmstat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final StatRepository repository;

    @Override
    public void saveHit(EndpointHit hit) {
        repository.save(hit);
    }

    @Override
    public List<ViewStats> getStats(StatsRequestParams params) {
        return null;
    }
}
