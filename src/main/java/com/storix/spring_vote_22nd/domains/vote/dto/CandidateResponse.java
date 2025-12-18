package com.storix.spring_vote_22nd.domains.vote.dto;

import com.storix.spring_vote_22nd.domains.vote.domain.Candidate;

public record CandidateResponse(
        Long id,
        String name,
        String description, // 파트명 or 팀명
        Long voteCount
) {
    public static CandidateResponse of(Candidate candidate, Long voteCount) {
        String desc = (candidate.getPart() != null) ? candidate.getPart().getValue()
                : (candidate.getTeam() != null) ? candidate.getTeam().getValue() : "";

        return new CandidateResponse(candidate.getId(), candidate.getName(), desc, voteCount);
    }
}