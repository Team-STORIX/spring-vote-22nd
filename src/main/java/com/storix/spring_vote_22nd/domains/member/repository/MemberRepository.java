package com.storix.spring_vote_22nd.domains.member.repository;

import com.storix.spring_vote_22nd.domains.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    Optional<MemberEntity> findByLoginId(String loginId);
}
