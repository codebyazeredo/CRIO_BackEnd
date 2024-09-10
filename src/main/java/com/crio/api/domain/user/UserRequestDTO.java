package com.crio.api.domain.user;

public record UserRequestDTO(
        String name,
        String email,
        String password,
        Integer type) {
}
