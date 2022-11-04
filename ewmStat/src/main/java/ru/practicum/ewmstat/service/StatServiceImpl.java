package ru.practicum.ewmstat.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewmstat.entity.EndpointHit;
import ru.practicum.ewmstat.repository.StatRepository;
import ru.practicum.ewmstat.specification.StatsRequestParams;
import ru.practicum.ewmstat.entity.ViewStats;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Класс-реализация интерфейса сервиса статистики {@link StatService}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    /**
     * Поле репозитория статистики {@link StatRepository}
     */
    private final StatRepository repository;


    @Override
    public void saveHit(@NonNull EndpointHit hit) {
        repository.save(hit);
    }

    @Override
    public List<ViewStats> getStats(@NonNull StatsRequestParams params) {
        List<EndpointHit> hits = repository.findAll(params.toSpecification());
        log.info("hits: {}", hits);
        List<ViewStats> viewStats = hits.stream()
                .filter(distinctByKey(EndpointHit::getUri))
                .map(this::mapHitToView)
                .collect(Collectors.toList());
        log.info("viewStats: {}", viewStats);
        return viewStats.stream()
                .peek(vs -> vs.setHits(repository.countHitsByUri(vs.getUri())))
                .peek(vs -> log.info("stat with hits: {}", vs))
                .collect(Collectors.toList());
    }

    /**
     * Метод маппинга {@link EndpointHit} в {@link ViewStats}
     *
     * @param hit {@link EndpointHit}
     * @return {@link ViewStats}
     */
    private ViewStats mapHitToView(@NonNull EndpointHit hit) {
        ViewStats viewStats = new ViewStats();
        viewStats.setApp(hit.getApp());
        viewStats.setUri(hit.getUri());
        return viewStats;
    }

    /**
     * Предикат для фильтрации {@link EndpointHit} по уникальным ip адресам
     */
    private static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
