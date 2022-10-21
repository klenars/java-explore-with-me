package ru.practicum.ewmstat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        List<EndpointHit> hits = repository.findAll(params.toSpecification());
        List<ViewStats> viewStats = hits.stream()
                .filter(distinctByKey(EndpointHit::getUri))
                .map(this::mapHitToView)
                .collect(Collectors.toList());

        if (params.isUnique()) {
            return viewStats.stream()
                    .peek(vs -> vs.setHits(repository.countDistinctByUri(vs.getUri(), params.getStart(), params.getEnd())))
                    .collect(Collectors.toList());
        } else {
            return viewStats.stream()
                    .peek(vs -> vs.setHits(repository.countByUri(vs.getUri(), params.getStart(), params.getEnd())))
                    .collect(Collectors.toList());
        }
    }

    private ViewStats mapHitToView(EndpointHit hit) {
        ViewStats viewStats = new ViewStats();
        viewStats.setApp(hit.getApp());
        viewStats.setUri(hit.getUri());
        return viewStats;
    }

    private static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
