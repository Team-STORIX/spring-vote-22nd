package com.storix.spring_vote_22nd.domains.user.dto;

public record LoginWithTokenResponse(
        String accessToken,
        String refreshToken
) {
}