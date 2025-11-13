package com.storix.spring_vote_22nd.domains.member.repository;

import com.storix.spring_vote_22nd.domains.member.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByMemberId(Long memberId);

    void deleteByToken(String token);
}
