package ru.practicum.ewmserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Базовый класс Entity Категория, содержит поля:
 * {@link Category#id},
 * {@link Category#name}
 */
@Entity(name = "categories")
@Getter
@Setter
public class Category {

    /**Идентификатор*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**Название*/
    @Column(unique = true)
    private String name;
}
