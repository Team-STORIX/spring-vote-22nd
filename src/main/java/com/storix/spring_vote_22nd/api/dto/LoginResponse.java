package com.storix.spring_vote_22nd.api.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}