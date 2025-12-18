package com.storix.spring_vote_22nd.domains.vote.domain;

import com.storix.spring_vote_22nd.domains.user.domain.Part;
import com.storix.spring_vote_22nd.domains.user.domain.Team;
import com.storix.spring_vote_22nd.global.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Candidate extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name; // 파트장 후보명 or 팀명

    // 투표수
    @Column(nullable = false)
    private Integer voteCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoteCategory category;

    @Enumerated(EnumType.STRING)
    private Part part;

    @Enumerated(EnumType.STRING)
    private Team team;

    public void updateVoteCount(Integer newCount) {
        this.voteCount = newCount;
    }

    public Candidate(String name, VoteCategory category, Part part, Team team) {
        this.name = name;
        this.category = category;
        this.part = part;
        this.team = team;
    }
}
