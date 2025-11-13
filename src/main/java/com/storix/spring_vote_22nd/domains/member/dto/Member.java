package com.storix.spring_vote_22nd.domains.member.dto;

import com.storix.spring_vote_22nd.domains.member.domain.MemberEntity;

public record Member(
        Long id,
        String loginId,
        String name
) {
    public static Member from(MemberEntity m) {
        return new Member(
                m.getId(),
                m.getLoginId(),
                m.getName()
        );
    }
}