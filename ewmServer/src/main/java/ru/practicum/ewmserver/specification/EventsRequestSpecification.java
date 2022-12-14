package ru.practicum.ewmserver.specification;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.practicum.ewmserver.api.publicApi.controller.requestParams.EventSortType;
import ru.practicum.ewmserver.api.publicApi.controller.requestParams.EventsRequestParams;
import ru.practicum.ewmserver.entity.Event;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Спецификация для фильтрации Событий на публичный запрос, имеет поле:
 * {@link EventsRequestSpecification#params}
 */
@AllArgsConstructor
public class EventsRequestSpecification implements Specification<Event> {

    /**Параметры запроса для фильтрации событий {@link EventsRequestParams}*/
    private EventsRequestParams params;

    @Override
    public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        String text = params.getText();
        if (text != null && !text.isBlank()) {
            predicates.add(
                    cb.or(
                            cb.like(cb.lower(root.get("annotation")), "%" + text.toLowerCase() + "%"),
                            cb.like(cb.lower(root.get("description")), "%" + text.toLowerCase() + "%")
                    )
            );
        }

        List<Long> categories = params.getCategories();
        if (categories != null && !categories.isEmpty()) {
            predicates.add(root.get("category").get("id").in(categories));
        }

        Boolean paid = params.getPaid();
        if (paid != null) {
            predicates.add(cb.equal(root.get("paid"), paid));
        }

        String rangeStart = params.getRangeStart();
        if (rangeStart != null) {
            LocalDateTime start = LocalDateTime.parse(rangeStart, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            predicates.add(
                    cb.greaterThanOrEqualTo(root.get("eventDate"), start)
            );
        } else {
            predicates.add(
                    cb.greaterThanOrEqualTo(root.get("eventDate"), LocalDateTime.now())
            );
        }

        String rangeEnd = params.getRangeEnd();
        if (rangeEnd != null) {
            LocalDateTime end = LocalDateTime.parse(rangeEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            predicates.add(
                    cb.lessThanOrEqualTo(root.get("eventDate"), end)
            );
        }

        Boolean onlyAvailable = params.getOnlyAvailable();
        if (onlyAvailable != null) {
            predicates.add(
                    cb.or(
                            cb.greaterThan(root.get("participantLimit"), root.get("confirmedRequests")),
                            cb.equal(root.get("participantLimit"), 0)
                    )
            );
        }

        EventSortType sortType = params.getSort();
        if (sortType != null) {
            switch (sortType) {
                case EVENT_DATE:
                    query.orderBy(cb.asc(root.get("eventDate")));
                    break;
                case VIEWS:
                    query.orderBy(cb.desc(root.get("views")));
                    break;
                case USER_RATING:
                    query.orderBy(cb.desc(root.get("initiator").get("rating")));
                    break;
            }
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
