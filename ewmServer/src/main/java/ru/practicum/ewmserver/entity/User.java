package ru.practicum.ewmserver.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Пользователь, содержит поля:
 * {@link User#id},
 * {@link User#name},
 * {@link User#email},
 */
@Entity(name = "users")
@Getter
@Setter
@ToString
public class User {

    /**Идентификатор*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**Имя*/
    private String name;

    /**Электронная почта*/
    @Column(unique = true)
    private String email;
}
