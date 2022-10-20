package ru.practicum.ewmserver.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "events")
@Getter
@Setter
@ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Category category;
    @Column(length = 120)
    private String title;
    @Column(length = 2000)
    private String annotation;
    private LocalDateTime eventDate;
    @ManyToOne
    private User initiator;
    @OneToOne
    private Location location;
    private boolean paid;
    private int confirmedRequests;
    private LocalDateTime createdOn;
    @Column(length = 7000)
    private String description;
    private int participantLimit;
    private LocalDateTime publishedOn;
    private boolean requestModeration;
    @Enumerated(EnumType.STRING)
    private EventState state;
    private long views;

    public Event() {
        this.createdOn = LocalDateTime.now();
        this.state = EventState.PENDING;
    }
}
