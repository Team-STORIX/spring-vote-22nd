package com.storix.spring_vote_22nd.api.dto;

public record LoginRequest(
        String loginId,
        String password
) {
}