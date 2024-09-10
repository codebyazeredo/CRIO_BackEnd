package com.crio.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crio.api.domain.event.Event;
import com.crio.api.domain.event.EventRequestDTO;
import com.crio.api.domain.user.User;
import com.crio.api.service.EventService;

@RestController
@RequestMapping("/api/event")
public class EventController {
    
    @Autowired
    private EventService eventService;

    @PostMapping(consumes="multipart/form-data")
    public ResponseEntity<Event> create(
        @RequestParam("title") String title,
        @RequestParam("description") String description,
        @RequestParam("startEvent") LocalDateTime startEvent,
        @RequestParam("endEvent") LocalDateTime endEvent,
        @RequestParam("local") String local,
        @RequestParam("privateEvent") Boolean privateEvent,
        @RequestParam("user") User user) {        
        
            EventRequestDTO eventRequestDTO = new EventRequestDTO(title, description, startEvent, endEvent, local, privateEvent, user);
            Event newEvent = this.eventService.createEvent(eventRequestDTO);
            return ResponseEntity.ok(newEvent);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        List<Event> eventList = this.eventService.getAllEvent();
        return ResponseEntity.ok(eventList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") UUID id){
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable UUID id, EventRequestDTO erdto){ 
        Event updatedEvent = this.eventService.updateEvent(id, erdto);      
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
    
    


