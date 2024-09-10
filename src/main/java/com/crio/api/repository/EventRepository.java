package com.crio.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.api.domain.event.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    //findEventsByTitle() implementar
    //findEventsByStartEvent() implementar
}
