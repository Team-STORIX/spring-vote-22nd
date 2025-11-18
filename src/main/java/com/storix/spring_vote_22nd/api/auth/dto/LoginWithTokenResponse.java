package com.storix.spring_vote_22nd.api.auth.dto;

import lombok.Builder;

@Builder
public record LoginWithTokenResponse(
        String accessToken,
        String refreshToken
) {
}