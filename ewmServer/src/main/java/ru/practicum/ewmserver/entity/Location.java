package ru.practicum.ewmserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "locations")
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double lat;

    private Double lon;

    @OneToOne
    private Event event;

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", eventId=" + (event != null ? event.getId() : "null") +
                '}';
    }
}