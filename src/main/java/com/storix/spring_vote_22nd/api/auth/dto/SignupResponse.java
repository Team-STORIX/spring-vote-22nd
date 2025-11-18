package com.storix.spring_vote_22nd.api.auth.dto;

public record SignupResponse(
        Long userId,
        String loginId,
        String name
) {
}
