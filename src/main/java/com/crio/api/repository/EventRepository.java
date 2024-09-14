package com.crio.api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crio.api.domain.event.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e WHERE e.user.id = :userId")
    List<Event> findByUserId(UUID userId);

    @Query("SELECT e FROM Event e WHERE e.startEvent BETWEEN :startEvent AND :endEvent")
    List<Event> findEventByIntervalData(LocalDateTime startEvent, LocalDateTime endEvent);

    @Query("SELECT e FROM Event e WHERE e.local = :local")
    List<Event> findByLocal(String local);

    @Query("SELECT e FROM Event e WHERE e.local = :local AND e.startEvent BETWEEN :startEvent AND :endEvent")
    List<Event> findByLocalAndIntervalData(String local, LocalDateTime startEvent, LocalDateTime endEvent);

    
}
