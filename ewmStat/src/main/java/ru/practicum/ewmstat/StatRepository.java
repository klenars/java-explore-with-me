package ru.practicum.ewmstat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StatRepository extends JpaRepository<EndpointHit, Long>, JpaSpecificationExecutor<EndpointHit> {
}
