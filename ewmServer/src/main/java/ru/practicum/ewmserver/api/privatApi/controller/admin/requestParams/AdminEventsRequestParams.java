package ru.practicum.ewmserver.api.privatApi.controller.admin.requestParams;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.practicum.ewmserver.specification.AdminEventsRequestSpecification;
import ru.practicum.ewmserver.entity.EventState;
import ru.practicum.ewmserver.entity.Event;

import java.util.List;

/**
 * Класс фильтрации Событий {@link Event} при запросе от админа, содержит поля:
 * {@link AdminEventsRequestParams#users},
 * {@link AdminEventsRequestParams#states},
 * {@link AdminEventsRequestParams#categories},
 * {@link AdminEventsRequestParams#rangeStart},
 * {@link AdminEventsRequestParams#rangeEnd},
 * {@link AdminEventsRequestParams#from},
 * {@link AdminEventsRequestParams#size}
 */
@Getter
@Setter
@ToString
public class AdminEventsRequestParams {

    /**список id пользователей, чьи события нужно найти*/
    private List<Long> users;

    /**список состояний в которых находятся искомые события*/
    private List<EventState> states;

    /**список id категорий в которых будет вестись поиск*/
    private List<Long> categories;

    /**дата и время не раньше которых должно произойти событие*/
    private String rangeStart;

    /**дата и время не позже которых должно произойти событие*/
    private String rangeEnd;

    /**количество событий, которые нужно пропустить для формирования текущего набора*/
    private int from;

    /**количество событий в наборе*/
    private int size;

    public AdminEventsRequestParams() {
        this.size = 10;
    }

    public AdminEventsRequestSpecification toSpecification() {
        return new AdminEventsRequestSpecification(this);
    }

    public Pageable toPageable() {
        return PageRequest.of(from / size, size);
    }
}
