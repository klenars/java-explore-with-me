package ru.practicum.ewmstat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import ru.practicum.ewmstat.entity.EndpointHit;

import java.time.LocalDateTime;

/**
 * Интерфейс сохранения данных статистики в БД, сохраняет записи класса {@link EndpointHit}
 */
public interface StatRepository extends JpaRepository<EndpointHit, Long>, JpaSpecificationExecutor<EndpointHit> {

    /**
     * Подсчет колличества обращений к url от уникальных ip адресов
     * @param uri целевой url-адрес
     * @param start начало интересующего периода
     * @param end конец интересующего периода
     * @return long колличество обращений
     */
    @Query("select count(distinct h.ip) from hits h where h.uri = ?1 and h.timestamp >= ?2 and h.timestamp <= ?3")
    long countDistinctByUri(String uri, LocalDateTime start, LocalDateTime end);

    /**
     * Подсчет колличества обращений к url
     * @param uri целевой url-адрес
     * @param start начало интересующего периода
     * @param end конец интересующего периода
     * @return long колличество обращений
     */

    @Query("select count(h) from hits h where h.uri = ?1 and h.timestamp >= ?2 and h.timestamp <= ?3")
    long countByUri(String uri, @Nullable LocalDateTime start, @Nullable LocalDateTime end);

    @Query("select count(h) from hits h where h.uri = ?1")
    long countHitsByUri(String uri);
}
