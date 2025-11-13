package com.storix.spring_vote_22nd.api;

public record LoginRequest(
        String loginId,
        String password
) {
}