package com.crio.api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.api.domain.event.Event;



@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    
    List<Event> findByTitle(String title);
    List<Event> findByStartEvent(LocalDateTime startEvent);
    List<Event> findByLocal(String local);
}
