package ru.practicum.ewmserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "events")
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Category category;

    private String title;

    private String annotation;

    private LocalDateTime eventDate;

    @ManyToOne
    private User initiator;

    @OneToOne
    private Location location;

    private boolean paid;

    private int confirmedRequests;

    private LocalDateTime createdOn;

    private String description;

    private int participantLimit;

    private LocalDateTime publishedOn;

    private boolean requestModeration;

    private EventState state;

    private int views;
}
