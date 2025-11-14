package com.storix.spring_vote_22nd.domains.member.service;

import com.storix.spring_vote_22nd.domains.member.domain.MemberEntity;
import com.storix.spring_vote_22nd.domains.member.domain.Part;
import com.storix.spring_vote_22nd.domains.member.domain.Team;

public record CreateMemberCommand(
        String loginId,
        String password,
        String email,
        Part part,
        String name,
        Team team
) {

    public MemberEntity toEntity() {
        return new MemberEntity(
                loginId,
                password,
                email,
                part,
                name,
                team
        );
    }
}
