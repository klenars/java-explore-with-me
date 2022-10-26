package ru.practicum.ewmstat.specification;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Класс параметров запроса данных статистики, содержит поля:
 * {@link StatsRequestParams#start},
 * {@link StatsRequestParams#end},
 * {@link StatsRequestParams#uris},
 * {@link StatsRequestParams#unique}
 */
@Getter
@Setter
public class StatsRequestParams {

    /**Начало периода*/
    private LocalDateTime start;
    /**Конец периода*/
    private LocalDateTime end;
    /**Список интересующих url*/
    private List<String> uris;
    /**Нужны ли данные только по уникальным адресам*/
    private boolean unique;

    /**
     * Парсинг начала периода из строки
     * @param start дата конца периода в формате "yyyy-MM-dd HH:mm:ss"
     */
    public void setStart(String start) {
        this.start = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Парсинг конца периода из строки
     * @param end дата конца периода в формате "yyyy-MM-dd HH:mm:ss"
     */
    public void setEnd(String end) {
        this.end = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Перевод параметров запроса в класс Спецификациии
     * @return {@link StatsRequestSpecification}
     */
    public StatsRequestSpecification toSpecification() {
        return new StatsRequestSpecification(this);
    }
}
