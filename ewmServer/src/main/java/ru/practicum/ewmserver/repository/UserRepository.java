package ru.practicum.ewmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewmserver.entity.User;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

/**
 * Интерфейс репозитория Юзеров {@link User}
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Плучение Юзера по id
     * @param id must not be {@literal null}.
     * @return {@link User}
     */
    default User getById(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("User id=%d not found!", id))
        );
    }

    /**
     * Получение списка Юзеров по списку id
     * @param ids список id
     * @param pageable {@link Pageable}
     * @return Page of {@link User}
     */
    @Query("select u from users u where u.id in ?1 order by u.id")
    Page<User> getAllByIds(Collection<Long> ids, Pageable pageable);
}
