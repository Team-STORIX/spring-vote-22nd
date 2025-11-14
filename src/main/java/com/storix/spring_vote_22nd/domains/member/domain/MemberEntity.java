package com.storix.spring_vote_22nd.domains.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class MemberEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;
    private String password;

    private String email;

    private Part part;
    private String name;
    private Team team;

    @Builder
    public MemberEntity(String loginId, String password, String email, Part part, String name, Team team) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.part = part;
        this.name = name;
        this.team = team;
    }

}
