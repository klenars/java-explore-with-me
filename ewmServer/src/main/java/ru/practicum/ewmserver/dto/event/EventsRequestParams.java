package ru.practicum.ewmserver.dto.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
@ToString
public class EventsRequestParams {
    private String text;
    private List<Long> categories;
    private Boolean paid;
    private String rangeStart;
    private String rangeEnd;
    private Boolean onlyAvailable;
    private EventSortType sort;
    private int from;
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
