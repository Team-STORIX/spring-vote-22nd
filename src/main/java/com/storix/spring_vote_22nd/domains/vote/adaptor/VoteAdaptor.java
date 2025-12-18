package com.storix.spring_vote_22nd.domains.vote.adaptor;

import com.storix.spring_vote_22nd.domains.vote.domain.Candidate;
import com.storix.spring_vote_22nd.domains.vote.domain.VoteCategory;
import com.storix.spring_vote_22nd.domains.vote.repository.CandidateRepository;
import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;
import com.storix.spring_vote_22nd.global.apiPayload.exception.STORIXCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class VoteAdaptor {

    private final CandidateRepository candidateRepository;
    private final RedisTemplate<String, String> redisTemplate;


    public Candidate findCandidateById(Long candidateId) {
        return candidateRepository.findById(candidateId)
                .orElseThrow(() -> new STORIXCodeException(ErrorCode.NOT_FOUND));
    }

    public List<Candidate> findAllCandidatesByCategory(VoteCategory category) {
        return candidateRepository.findAllByCategory(category);
    }

    // Redis Key 생성 메서드
    private String getRankKey(VoteCategory category) {
        return "vote:rank:" + category.name();
    }

    private String getHistoryKey(Long userId, VoteCategory category) {
        return "vote:history:" + category.name() + ":" + userId;
    }

    // true: 첫 투표(성공) / false: 중복
    public boolean setVoteHistoryIfAbsent(Long userId, VoteCategory category) {
        String key = getHistoryKey(userId, category);
        Boolean success = redisTemplate.opsForValue().setIfAbsent(key, "VOTED");

        if (Boolean.TRUE.equals(success)) {
            redisTemplate.expire(key, 24, TimeUnit.HOURS);
        }
        return Boolean.TRUE.equals(success);
    }

    // 득표수 증가 (ZSET)
    public void addVoteCount(VoteCategory category, Long candidateId) {
        redisTemplate.opsForZSet().incrementScore(getRankKey(category), candidateId.toString(), 1.0);
    }

    // 스케줄러용: 해당 카테고리의 모든 후보자 득표수 조회
    public Set<ZSetOperations.TypedTuple<String>> getAllVoteScores(VoteCategory category) {
        return redisTemplate.opsForZSet().rangeWithScores(getRankKey(category), 0, -1);
    }

    // 특정 후보의 득표수 조회
    public Long getVoteCount(VoteCategory category, Long candidateId) {
        Double score = redisTemplate.opsForZSet().score(getRankKey(category), candidateId.toString());
        return score != null ? score.longValue() : 0L;
    }
}