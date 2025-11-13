package com.storix.spring_vote_22nd.api;

public record SignUpRequest(
        String name,
        String loginId,
        String password
) {
}