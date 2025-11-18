package com.storix.spring_vote_22nd.domains.user.dto;

public record LoginInfo(
        String loginId,
        String password
) {
}