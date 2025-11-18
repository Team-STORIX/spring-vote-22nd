package com.storix.spring_vote_22nd.domains.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 계정 정보
    @Column(unique = true)
    private String loginId;
    private String password;

    @Column(unique = true)
    private String email;

    private Part part;
    private String name;
    private Team team;

    private Role role = Role.USER;

    @Builder
    public User(String loginId, String password, String email, Part part, String name, Team team) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.part = part;
        this.name = name;
        this.team = team;
    }

}
