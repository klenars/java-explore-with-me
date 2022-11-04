package ru.practicum.ewmserver.service.client;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ViewsList implements Serializable {
    private List<ViewStats> views;

    public ViewsList() {
        this.views = new ArrayList<>();
    }
}
