package ru.practicum.ewmserver.dto.admin;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.entity.EventState;

import java.util.List;

@Getter
@Setter
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
}
