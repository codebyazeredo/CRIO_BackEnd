package com.crio.api.domain.event;

import java.time.LocalDateTime;

import com.crio.api.domain.user.User;

public record EventRequestDTO(
        String title,
        String description,
        LocalDateTime startEvent,
        LocalDateTime endEvent,
        String local,
        Boolean privateEvent,
        User user
) {

}
