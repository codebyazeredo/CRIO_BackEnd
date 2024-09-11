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

    public Event createEvent(EventRequestDTO erdto){

        Event newEvent = new Event();
        newEvent.setTitle(erdto.title());
        newEvent.setDescription(erdto.description());
        newEvent.setStartEvent(erdto.startEvent());
        newEvent.setEndEvent(erdto.endEvent());
        newEvent.setLocal(erdto.local());
        newEvent.setPrivateEvent(erdto.privateEvent());
        newEvent.setUser(erdto.user());
        
        eventRepository.save(newEvent);
        return newEvent;
    }

    public List<Event> getAllEvent(){
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(UUID id){
        return Optional.of(eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found")));
    }

    public Event updateEvent(UUID id, EventRequestDTO erdto){
        
        Event updatedEvent = getEventById(id).orElseThrow();
        
        updatedEvent.setTitle(erdto.title());
        updatedEvent.setDescription(erdto.description());
        updatedEvent.setStartEvent(erdto.startEvent());
        updatedEvent.setEndEvent(erdto.endEvent());
        updatedEvent.setLocal(erdto.local());
        updatedEvent.setPrivateEvent(erdto.privateEvent());
        updatedEvent.setUser(erdto.user());
        return eventRepository.save(updatedEvent);
    }

    public void deleteEvent (UUID id){
        eventRepository.deleteById(id);
    }

}
