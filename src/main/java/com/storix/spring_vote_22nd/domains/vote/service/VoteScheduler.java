package com.storix.spring_vote_22nd.domains.vote.service;

import com.storix.spring_vote_22nd.domains.vote.adaptor.VoteAdaptor;
import com.storix.spring_vote_22nd.domains.vote.domain.VoteCategory;
import com.storix.spring_vote_22nd.domains.vote.repository.CandidateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class VoteScheduler {

    private final VoteAdaptor voteAdaptor;
    private final CandidateRepository candidateRepository;

    @Scheduled(fixedDelay = 60000) // 1분
    @Transactional
    public void syncVoteCountsFromRedis() {
        for (VoteCategory category : VoteCategory.values()) {
            syncCategoryVotes(category);
        }
    }

    private void syncCategoryVotes(VoteCategory category) {
        // Redis ZSET에서 해당 카테고리의 모든 (후보자ID, 점수) 가져옴
        Set<ZSetOperations.TypedTuple<String>> scores = voteAdaptor.getAllVoteScores(category);

        if (scores == null || scores.isEmpty()) return;

        for (ZSetOperations.TypedTuple<String> tuple : scores) {
            try {
                Long candidateId = Long.parseLong(tuple.getValue()); // 후보자 id
                Double voteCountDouble = tuple.getScore();           // 득표수
                int redisVoteCount = (voteCountDouble != null) ? voteCountDouble.intValue() : 0;

                // db 업데이트 - Dirty Checking
                candidateRepository.findById(candidateId).ifPresent(candidate -> {
                    if (candidate.getVoteCount() != redisVoteCount) {
                        candidate.updateVoteCount(redisVoteCount);
                        log.info("[Sync] {} - ID:{}, DB:{}, Redis:{}",
                                category, candidateId, candidate.getVoteCount(), redisVoteCount);
                    }
                });
            } catch (Exception e) {
                log.error("동기화 에러 - Category: {}, Tuple: {}", category, tuple.getValue(), e);
            }
        }
    }
}