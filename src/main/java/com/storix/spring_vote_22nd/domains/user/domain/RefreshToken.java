package com.storix.spring_vote_22nd.domains.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "refreshToken")
@Getter
public class RefreshToken {
    @Id
    private String id;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long ttl;

    @Builder
    public RefreshToken(String id, String refreshToken, Long ttl) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.ttl = ttl;
    }

}