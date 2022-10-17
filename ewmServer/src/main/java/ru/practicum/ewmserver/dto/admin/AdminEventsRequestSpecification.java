package ru.practicum.ewmserver.dto.admin;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.practicum.ewmserver.entity.Event;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AdminEventsRequestSpecification implements Specification<Event> {

    private AdminEventsRequestParams params;

    @Override
    public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (params.getUsers() != null && !params.getUsers().isEmpty()) {
            predicates.add(root.get("initiator").get("id").in(params.getUsers()));
        }

        if (params.getStates() != null && !params.getStates().isEmpty()) {
            predicates.add(root.get("state").in(params.getStates()));
        }

        if (params.getCategories() != null && !params.getCategories().isEmpty()) {
            predicates.add(root.get("category").get("id").in(params.getCategories()));
        }

        if (params.getRangeStart() != null && !params.getRangeStart().isEmpty()) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                    root.get("eventDate"),
                    LocalDateTime.parse(params.getRangeStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            );
        }

        if (params.getRangeEnd() != null && !params.getRangeEnd().isEmpty()) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(
                    root.get("eventDate"),
                    LocalDateTime.parse(params.getRangeEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
