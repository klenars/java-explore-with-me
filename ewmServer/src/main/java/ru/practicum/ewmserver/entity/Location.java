package ru.practicum.ewmserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Широта и долгота места проведения события, содержит поля:
 * {@link Location#id},
 * {@link Location#lat},
 * {@link Location#lon},
 * {@link Location#event},
 */
@Entity(name = "locations")
@Getter
@Setter
public class Location {

    /**Идентификатор*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**Широта*/
    private Double lat;

    /**Долгота*/
    private Double lon;

    /**Событие*/
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
