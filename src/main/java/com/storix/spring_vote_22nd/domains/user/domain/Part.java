package com.storix.spring_vote_22nd.domains.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Part {
    FE("프론트엔드"),
    BE("백엔드");

    private String value;
}
