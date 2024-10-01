package com.crio.api.domain.user;

public record UserRequestDTO(
        String username,
        String email,
        String password,
        Integer type) {

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getType() {
        return type;
    }
}

