package com.storix.spring_vote_22nd.domains.user.repository;

import com.storix.spring_vote_22nd.domains.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByLoginId(String loginId);

    Optional<User> findUserByEmail(String email);
}
