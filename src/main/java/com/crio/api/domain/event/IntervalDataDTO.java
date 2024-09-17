package com.crio.api.domain.event;

import java.time.LocalDateTime;

public record IntervalDataDTO(
        LocalDateTime startEvent,
        LocalDateTime endEvent) {

}
