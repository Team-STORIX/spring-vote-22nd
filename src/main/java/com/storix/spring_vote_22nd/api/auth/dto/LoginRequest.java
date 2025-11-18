package com.storix.spring_vote_22nd.api.auth.dto;

public record LoginRequest(
        String loginId,
        String password
) {
}