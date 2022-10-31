package ru.practicum.ewmserver.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * Пользователь, содержит поля:
 * {@link User#id},
 * {@link User#name},
 * {@link User#email},
 * {@link User#rating}
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

    /**Рейтинг юзера по рейтингу его прошедших событий*/
    private Double rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
