package ru.practicum.ewmserver.api.privatApi.controller.admin.requestParams;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.practicum.ewmserver.specification.AdminEventsRequestSpecification;
import ru.practicum.ewmserver.entity.EventState;

import java.util.List;

@Getter
@Setter
@ToString
public class AdminEventsRequestParams {
    private List<Long> users;
    private List<EventState> states;
    private List<Long> categories;
    private String rangeStart;
    private String rangeEnd;
    private int from;
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
