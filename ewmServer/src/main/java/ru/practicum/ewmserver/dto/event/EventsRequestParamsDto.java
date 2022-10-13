package ru.practicum.ewmserver.dto.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EventsRequestParamsDto {
    private String text;
    private List<Integer> categories;
    private boolean paid;
    private String rangeStart;
    private String rangeEnd;
    private boolean onlyAvailable;
    private EventSortType sort;
    private int from;
    private int size;
}
