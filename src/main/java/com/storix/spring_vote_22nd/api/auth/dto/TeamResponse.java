package com.storix.spring_vote_22nd.api.auth.dto;

import com.storix.spring_vote_22nd.domains.user.domain.Team;

public record TeamResponse(
        String code,
        String label
) {
    public static TeamResponse from(Team team) {
        return new TeamResponse(team.name(), team.getValue());
    }
}
