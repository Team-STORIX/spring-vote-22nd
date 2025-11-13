package com.storix.spring_vote_22nd.domains.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
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