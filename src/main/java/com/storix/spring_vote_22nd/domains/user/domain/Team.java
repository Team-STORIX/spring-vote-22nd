package com.storix.spring_vote_22nd.domains.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Team {
    DIGGINDIE("DiggIndie"),
    MODELLY("Modelly"),
    CATCHUP("Catch Up"),
    GROOMEASY("GroomEasy"),
    STORIX("STORIX"),;

    private String value;
}
