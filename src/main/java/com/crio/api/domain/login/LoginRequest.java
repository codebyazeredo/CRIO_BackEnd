package com.crio.api.domain.login;

public record LoginRequest(
    String username, String password) {
}
