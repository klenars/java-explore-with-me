package ru.practicum.ewmserver.dto.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EventsRequestParams {
    private String text;
    private List<Long> categories;
    private boolean paid;
    private String rangeStart;
    private String rangeEnd;
    private boolean onlyAvailable;
    private EventSortType sort;
    private int from;
    private int size;

    public EventsRequestParams() {
        this.size = 10;
    }
}
