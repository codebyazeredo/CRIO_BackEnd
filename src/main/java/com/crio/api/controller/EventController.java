package com.crio.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crio.api.domain.address.Address;
import com.crio.api.domain.event.Event;
import com.crio.api.domain.event.EventRequestDTO;
import com.crio.api.domain.event.IntervalDataDTO;
import com.crio.api.domain.event.LocalIntervalDTO;
import com.crio.api.domain.user.User;
import com.crio.api.service.AddressService;
import com.crio.api.service.EventService;
import com.crio.api.service.UserService;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    //QUERY
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Event>> findByUserId(@PathVariable UUID userId) {
        List<Event> events = eventService.findByUserId(userId);
        return ResponseEntity.ok(events);
    }

    @PostMapping("/interval")
    public ResponseEntity<List<Event>> findEventByIntervalData(@RequestParam IntervalDataDTO dto) {
        List<Event> events = eventService.findEventByIntervalData(dto);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/local/{local}")
    public ResponseEntity<List<Event>> findByLocal(@PathVariable String local) {
        List<Event> events = eventService.findByLocal(local);
        return ResponseEntity.ok(events);
    }

    @PostMapping("/local-interval")
    public ResponseEntity<List<Event>> findLocalAndIntervalData(@RequestParam LocalIntervalDTO dto) {
        List<Event> events = eventService.findByLocalAndIntervalData(
                dto.local(),
                dto.startEvent(),
                dto.endEvent()
        );
        return ResponseEntity.ok(events);
    }

    //CRUD
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> create(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("startEvent") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startEvent,
            @RequestParam("endEvent") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endEvent,
            @RequestParam("local") String local,
            @RequestParam("howToGet") String howToGet,
            @RequestParam("linkEvent") String linkEvent,
            @RequestParam("privateEvent") Boolean privateEvent,
            @RequestParam("userId") UUID userId,
            @RequestParam("addressId") UUID addressId) {

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Address address = addressService.getAddressById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        EventRequestDTO eventRequestDTO = new EventRequestDTO(title, description, startEvent, endEvent, local, howToGet, linkEvent, privateEvent, user, address);
        Event newEvent = this.eventService.createEvent(eventRequestDTO);
        return ResponseEntity.ok(newEvent);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        List<Event> eventList = this.eventService.getAllEvent();
        return ResponseEntity.ok(eventList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") UUID id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") UUID id, EventRequestDTO dto) {
        Event updatedEvent = this.eventService.updateEvent(id, dto);
        System.out.println(dto);
        return ResponseEntity.ok(updatedEvent);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") UUID id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
