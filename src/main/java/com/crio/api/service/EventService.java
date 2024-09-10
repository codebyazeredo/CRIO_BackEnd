package com.crio.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.api.domain.event.Event;
import com.crio.api.domain.event.EventRequestDTO;
import com.crio.api.repository.EventRepository;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(EventRequestDTO dto){

        Event newEvent = new Event();
        newEvent.setTitle(dto.title());
        newEvent.setDescription(dto.description());
        newEvent.setStartEvent(dto.startEvent());
        newEvent.setEndEvent(dto.endEvent());
        newEvent.setLocal(dto.local());
        newEvent.setPrivateEvent(dto.privateEvent());
        newEvent.setUser(dto.user());
        
        eventRepository.save(newEvent);
        return newEvent;
    }

    public List<Event> getAllEvent(){
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(UUID id){
        return Optional.of(eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found")));
    }

    public Event updateEvent(UUID id, EventRequestDTO dto){
        
        Event updatedEvent = getEventById(id).orElseThrow();
        
        updatedEvent.setTitle(dto.title());
        updatedEvent.setDescription(dto.description());
        updatedEvent.setStartEvent(dto.startEvent());
        updatedEvent.setEndEvent(dto.endEvent());
        updatedEvent.setLocal(dto.local());
        updatedEvent.setPrivateEvent(dto.privateEvent());
        updatedEvent.setUser(dto.user());
        return eventRepository.save(updatedEvent);
    }

    public void deleteEvent (UUID id){
        eventRepository.deleteById(id);
    }

}
