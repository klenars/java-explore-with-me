package ru.practicum.ewmstat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatRepository extends JpaRepository<EndpointHit, Long> {


}
