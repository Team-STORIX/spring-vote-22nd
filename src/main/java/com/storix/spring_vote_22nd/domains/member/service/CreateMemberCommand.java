package com.storix.spring_vote_22nd.domains.member.service;

import com.storix.spring_vote_22nd.domains.member.domain.MemberEntity;

public record CreateMemberCommand(
        String name,
        String loginId,
        String password
) {

    public MemberEntity toEntity() {
        return new MemberEntity(
                name,
                loginId,
                password
        );
    }
}
