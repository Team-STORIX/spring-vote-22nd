package com.storix.spring_vote_22nd.domains.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Team {

    DiggIndie("DiggIndie"),
    Modelly("Modelly"),
    CatchUp("CatchUp"),
    Menual("Menual"),
    STORIX("STORIX");

    private String value;
}
