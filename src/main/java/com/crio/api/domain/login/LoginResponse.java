package com.crio.api.domain.login;

public record LoginResponse(
    String accessToken, Long expiresIn) {
}
