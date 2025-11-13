package com.storix.spring_vote_22nd.domains.member.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Long memberId;

    private LocalDateTime expiryDateTime;

    @Builder
    public RefreshToken(String token, Long memberId, LocalDateTime expiryDateTime) {
        this.token = token;
        this.memberId = memberId;
        this.expiryDateTime = expiryDateTime;
    }
}