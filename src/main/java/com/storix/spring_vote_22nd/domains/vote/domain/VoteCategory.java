package com.storix.spring_vote_22nd.domains.vote.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VoteCategory {
    PART_LEADER("파트장 투표"),
    DEMO_DAY("데모데이 투표");

    private final String description;
}