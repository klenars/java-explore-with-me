package ru.practicum.ewmserver.api.publicApi.controller.requestParams;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.practicum.ewmserver.entity.Event;
import ru.practicum.ewmserver.specification.EventsRequestSpecification;

import java.util.List;

/**
 * Класс фильтрации Событий {@link Event} при запросе от пользователя, содержит поля:
 * {@link EventsRequestParams#text},
 * {@link EventsRequestParams#categories},
 * {@link EventsRequestParams#paid},
 * {@link EventsRequestParams#rangeStart},
 * {@link EventsRequestParams#rangeEnd},
 * {@link EventsRequestParams#onlyAvailable},
 * {@link EventsRequestParams#sort},
 * {@link EventsRequestParams#from},
 * {@link EventsRequestParams#size},
 */
@Getter
@Setter
@ToString
public class EventsRequestParams {

    /**текст для поиска в содержимом аннотации и подробном описании события*/
    private String text;

    /**список идентификаторов категорий в которых будет вестись поиск*/
    private List<Long> categories;

    /**поиск только платных/бесплатных событий*/
    private Boolean paid;

    /**дата и время не раньше которых должно произойти событие*/
    private String rangeStart;

    /**дата и время не позже которых должно произойти событие*/
    private String rangeEnd;

    /**только события у которых не исчерпан лимит запросов на участие*/
    private Boolean onlyAvailable;

    /**Вариант сортировки: по дате события или по количеству просмотров*/
    private EventSortType sort;

    /**количество событий, которые нужно пропустить для формирования текущего набора*/
    private int from;

    /**количество событий в наборе*/
    private int size;

    public EventsRequestParams() {
        this.size = 10;
    }

    public EventsRequestSpecification toSpecification() {
        return new EventsRequestSpecification(this);
    }

    public Pageable toPageable() {
        return PageRequest.of(from / size, size);
    }
}
