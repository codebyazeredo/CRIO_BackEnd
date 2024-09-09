package com.crio.api.domain.user;

import java.util.UUID;

public record UserResponseDTO(
    UUID id,    
    String name,
    String email,
    String password,
    Integer type
) {

}
