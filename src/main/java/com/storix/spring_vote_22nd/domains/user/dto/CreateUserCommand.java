package com.storix.spring_vote_22nd.domains.user.dto;

import com.storix.spring_vote_22nd.domains.user.domain.User;
import com.storix.spring_vote_22nd.domains.user.domain.Part;
import com.storix.spring_vote_22nd.domains.user.domain.Team;

public record CreateUserCommand(
        String loginId,
        String password,
        String email,
        Part part,
        String name,
        Team team
) {

    public User toEntity() {
        return new User(
                loginId,
                password,
                email,
                part,
                name,
                team
        );
    }
}
