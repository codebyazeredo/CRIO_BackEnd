package com.crio.api.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;

import com.crio.api.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="event")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name="start_Event")
    private LocalDateTime startEvent;

    @Column(name="end_Event")
    private LocalDateTime endEvent;

    @Column(name="local",  length = 255, nullable = false)
    private String local;

    @Column(name = "private_Event")
    @ColumnDefault("true")
    private Boolean privateEvent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
