package com.crio.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.api.domain.address.Address;
import com.crio.api.domain.event.Event;
import com.crio.api.domain.event.EventRequestDTO;
import com.crio.api.domain.event.IntervalDataDTO;
import com.crio.api.domain.user.User;
import com.crio.api.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    public Event createEvent(EventRequestDTO erdto) {
        User user = userService.getUserById(erdto.user().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Address address = addressService.getAddressById(erdto.address().getId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Event newEvent = new Event();
        newEvent.setTitle(erdto.title());
        newEvent.setDescription(erdto.description());
        newEvent.setStartEvent(erdto.startEvent());
        newEvent.setEndEvent(erdto.endEvent());
        newEvent.setLocal(erdto.local());
        newEvent.setHowToGet(erdto.howToGet()); 
        newEvent.setLinkEvent(erdto.linkEvent()); 
        newEvent.setPrivateEvent(erdto.privateEvent());
        newEvent.setUser(user);
        newEvent.setAddress(address); 

        eventRepository.save(newEvent);
        return newEvent;
    }

    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(UUID id) {
        return Optional.of(eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found")));
    }

    public Event updateEvent(UUID id, EventRequestDTO erdto) {
        Event updatedEvent = getEventById(id).orElseThrow();

        User user = userService.getUserById(erdto.user().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Address address = addressService.getAddressById(erdto.address().getId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        updatedEvent.setTitle(erdto.title());
        updatedEvent.setDescription(erdto.description());
        updatedEvent.setStartEvent(erdto.startEvent());
        updatedEvent.setEndEvent(erdto.endEvent());
        updatedEvent.setLocal(erdto.local());
        updatedEvent.setHowToGet(erdto.howToGet());
        updatedEvent.setLinkEvent(erdto.linkEvent());
        updatedEvent.setPrivateEvent(erdto.privateEvent());
        updatedEvent.setUser(user);
        updatedEvent.setAddress(address);

        return eventRepository.save(updatedEvent);
    }

    public void deleteEvent(UUID id) {
        eventRepository.deleteById(id);
    }

    public List<Event> findByUserId(UUID userId) {
        return eventRepository.findByUserId(userId);
    }

    public List<Event> findEventByIntervalData(IntervalDataDTO dto) {
        LocalDateTime startEvent = dto.startEvent();
        LocalDateTime endEvent = dto.endEvent();
        return eventRepository.findEventByIntervalData(startEvent, endEvent);
    }

    public List<Event> findByLocal(String local) {
        return eventRepository.findByLocal(local);
    }

    public List<Event> findByLocalAndIntervalData(String local, LocalDateTime startEvent, LocalDateTime endEvent) {
        return eventRepository.findByLocalAndIntervalData(local, startEvent, endEvent);
    }
}
