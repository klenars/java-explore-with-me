package ru.practicum.ewmserver.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmserver.dto.event.EventFullDto;
import ru.practicum.ewmserver.dto.event.EventShortDto;
import ru.practicum.ewmserver.dto.event.NewEventDto;
import ru.practicum.ewmserver.dto.event.UpdateEventRequest;
import ru.practicum.ewmserver.dto.participationRequest.ParticipationRequestDto;
import ru.practicum.ewmserver.entity.Category;
import ru.practicum.ewmserver.entity.Event;
import ru.practicum.ewmserver.entity.Location;
import ru.practicum.ewmserver.entity.User;
import ru.practicum.ewmserver.error.ForbiddenError;
import ru.practicum.ewmserver.mapper.EventMapper;
import ru.practicum.ewmserver.repository.CategoryRepository;
import ru.practicum.ewmserver.repository.EventRepository;
import ru.practicum.ewmserver.repository.LocationRepository;
import ru.practicum.ewmserver.repository.UserRepository;
import ru.practicum.ewmserver.service.UsersService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    //TODO
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    @Override
    public List<EventShortDto> getAll(long userId, int from, int size) {
        return null;
    }

    @Override
    @Transactional
    public EventFullDto createEvent(long userId, NewEventDto newEventDto) {
        User initiator = userRepository.getById(userId);
        Category category = categoryRepository.getById(newEventDto.getCategory());
        Event newEvent = eventMapper.toEntity(newEventDto, category, initiator);
        checkNewEventDate(newEvent);
        log.info("newEvent after mapping from NewEventDto: {}", newEvent);

        Location location = locationRepository.save(newEvent.getLocation());
        newEvent = eventRepository.save(newEvent);
        location.setEvent(newEvent);
        log.info("newEvent after save: {}", newEvent);

        return eventMapper.toFullDto(newEvent);
    }

    @Override
    public EventFullDto updateEvent(long userId, UpdateEventRequest updateEventRequest) {
        return null;
    }

    @Override
    public EventFullDto getById(long userId, long eventId) {
        return null;
    }

    @Override
    public EventFullDto cancelEvent(long userId, long eventId) {
        return null;
    }

    @Override
    public List<ParticipationRequestDto> getRequests(long userId, long eventId) {
        return null;
    }

    @Override
    public ParticipationRequestDto confirmRequest(long userId, long eventId, long reqId) {
        return null;
    }

    @Override
    public ParticipationRequestDto rejectRequest(long userId, long eventId, long reqId) {
        return null;
    }

    private void checkNewEventDate(Event newEvent) {
        if (newEvent.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new ForbiddenError(
                    String.format("До события %s осталось меньше 2 часов!", newEvent.getTitle())
            );
        }
    }

}
