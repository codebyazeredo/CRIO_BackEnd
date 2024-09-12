package com.crio.api.domain.event;

import java.time.LocalDateTime;

import com.crio.api.domain.address.Address;
import com.crio.api.domain.user.User;

public record EventRequestDTO(
        String title,
        String description,
        LocalDateTime startEvent,
        LocalDateTime endEvent,
        String local,
        String howToGet,
        String linkEvent,
        Boolean privateEvent,
        User user,
        Address address) {

}
