package ru.practicum.ewmserver.service.client;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.practicum.ewmserver.dto.client.EndpointHitDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис клиент для сохранения данных статистики, имеет поля:
 * {@link StatClient#restTemplate},
 * {@link StatClient#statUrl}
 */
@Service
public class StatClient {

    private final RestTemplate restTemplate;

    @Value("${ewmstat.url}")
    private String statUrl;

    public StatClient() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Отправка запроса на сохранение данных статистики
     * @param request {@link HttpServletRequest}
     */
    public void postHit(@NonNull HttpServletRequest request) {
        EndpointHitDto hitDto = new EndpointHitDto();
        hitDto.setApp("explore-with-me");
        hitDto.setUri(request.getRequestURI());
        hitDto.setIp(request.getRemoteAddr());
        hitDto.setTimestamp(LocalDateTime.now());

        makeAndSendRequest(hitDto);
    }

    /**
     * Подготовка и отправка запроса
     * @param body {@link EndpointHitDto}
     */
    private void makeAndSendRequest(@NonNull EndpointHitDto body) {
        HttpEntity<EndpointHitDto> requestEntity = new HttpEntity<>(body, defaultHeaders());
        restTemplate.exchange(statUrl, HttpMethod.POST, requestEntity, Object.class);
    }

    /**
     * Присвоение запросу стандартных заголовков
     * @return {@link HttpHeaders}
     */
    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }
}
