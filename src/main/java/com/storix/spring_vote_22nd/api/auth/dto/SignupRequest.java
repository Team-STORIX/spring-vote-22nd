package com.storix.spring_vote_22nd.api.auth.dto;

import com.storix.spring_vote_22nd.domains.user.domain.Part;
import com.storix.spring_vote_22nd.domains.user.domain.Team;

public record SignupRequest(
        String loginId,
        String password,
        String email,
        Part part,
        String name,
        Team team
) {
}