package ru.practicum.ewmserver.dto.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Класс отправки данных для сохранения статистики, содержит поля:
 * {@link EndpointHitDto#id},
 * {@link EndpointHitDto#app},
 * {@link EndpointHitDto#uri},
 * {@link EndpointHitDto#ip},
 * {@link EndpointHitDto#timestamp}
 */
@Getter
@Setter
public class EndpointHitDto {
    /**Id класса, генерируется БД*/
    private Long id;
    /**Поле указатель на сервис статистика которого сохраняется*/
    private String app;
    /**Ссылка по которой было обращение*/
    private String uri;
    /**Ip адрес с которого было обращение к эндпоинту*/
    private String ip;
    /**Время обращения*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
}
