package ru.practicum.ewmclient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class StatsClient {

    private final RestTemplate restTemplate;
}
