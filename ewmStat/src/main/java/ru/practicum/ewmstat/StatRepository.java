package ru.practicum.ewmstat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface StatRepository extends JpaRepository<EndpointHit, Long>, JpaSpecificationExecutor<EndpointHit> {

    @Query("select count(distinct h.ip) from hits h where h.uri = ?1 and h.timestamp >= ?2 and h.timestamp <= ?3")
    long countDistinctByUri(String uri, LocalDateTime start, LocalDateTime end);

    @Query("select count(h) from hits h where h.uri = ?1 and h.timestamp >= ?2 and h.timestamp <= ?3")
    long countByUri(String uri, LocalDateTime start, LocalDateTime end);
}
