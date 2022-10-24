package ru.practicum.ewmstat.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс для сохранения данных статистики обращений к эндпоинтам в БД,
 * имеет поля:
 * {@link EndpointHit#id},
 * {@link EndpointHit#app},
 * {@link EndpointHit#uri},
 * {@link EndpointHit#ip},
 * {@link EndpointHit#timestamp}
 */
@Entity(name = "hits")
@Getter
@Setter
public class EndpointHit {

    /**Id класса, генерируется БД*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**Поле указатель на сервис статистика которого сохраняется*/
    private String app;
    /**Ссылка по которой было обращение*/
    private String uri;
    /**Ip адрес с которого было обращение к эндпоинту*/
    private String ip;
    /**Время обращения*/
    private LocalDateTime timestamp;

    /**
     * Приводит входящий параметр времени обращения к классу {@link LocalDateTime}
     * @param timestamp входящий параметр времени в формате String "yyyy-MM-dd HH:mm:ss"
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
