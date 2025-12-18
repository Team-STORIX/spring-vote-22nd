package com.storix.spring_vote_22nd.domains.vote.repository;

import com.storix.spring_vote_22nd.domains.vote.domain.Candidate;
import com.storix.spring_vote_22nd.domains.vote.domain.VoteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findAllByCategory(VoteCategory category);
}
