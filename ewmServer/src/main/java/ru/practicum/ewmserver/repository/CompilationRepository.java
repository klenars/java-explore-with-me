package ru.practicum.ewmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewmserver.entity.Compilation;

import javax.persistence.EntityNotFoundException;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {

    default Compilation getById(long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Compilation id=%d not found!", id))
        );
    }

    @Query("select c from Compilation c where c.pinned = ?1")
    Page<Compilation> findAllCompilationByPinning(boolean pinned, Pageable pageable);
}
