package ru.practicum.ewmstat.specification;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.practicum.ewmstat.entity.EndpointHit;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс спецификации для фильтрации данных статистики из БД
 */
@AllArgsConstructor
public class StatsRequestSpecification implements Specification<EndpointHit> {

    /**Параметры запроса {@link StatsRequestParams}*/
    private StatsRequestParams params;

    @Override
    public Predicate toPredicate(Root<EndpointHit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        LocalDateTime start = params.getStart();
        if (start != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("timestamp"), start));
        }

        LocalDateTime end = params.getEnd();
        if (start != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("timestamp"), end));
        }

        List<String> uris = params.getUris();
        if (uris != null && !uris.isEmpty()) {
            predicates.add(root.get("uri").in(uris));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
