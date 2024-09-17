package com.crio.api.domain.event;

import java.time.LocalDateTime;

public record LocalIntervalDTO(
    String local,
    LocalDateTime startEvent,
    LocalDateTime endEvent
) {

}
