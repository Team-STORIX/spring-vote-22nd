package com.storix.spring_vote_22nd.api;

import com.storix.spring_vote_22nd.domains.member.domain.Part;
import com.storix.spring_vote_22nd.domains.member.domain.Team;

public record SignUpRequest(
        String loginId,
        String password,
        String email,
        Part part,
        String name,
        Team team
) {
}