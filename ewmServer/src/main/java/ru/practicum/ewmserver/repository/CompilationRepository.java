package ru.practicum.ewmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewmserver.entity.Compilation;

import javax.persistence.EntityNotFoundException;

/**
 * Интерфейс репозиоря для сохранения {@link Compilation}
 */
public interface CompilationRepository extends JpaRepository<Compilation, Long> {

    /**
     * Получение Compilation по id
     * @param id id подборки
     * @return {@link Compilation}
     */
    default Compilation getById(long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Compilation id=%d not found!", id))
        );
    }

    /**
     * Получение Page<Compilation> закрепленных или нет подборок событий на главной странице
     * @param pinned закрепленны или нет на главной странице
     * @param pageable {@link Pageable}
     * @return Page of {@link Compilation}
     */
    @Query("select c from Compilation c where c.pinned = ?1")
    Page<Compilation> findAllCompilationByPinning(boolean pinned, Pageable pageable);
}
