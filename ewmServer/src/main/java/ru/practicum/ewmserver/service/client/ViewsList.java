package ru.practicum.ewmserver.service.client;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ViewsList {
    private List<ViewStats> views = new ArrayList<>();
}
