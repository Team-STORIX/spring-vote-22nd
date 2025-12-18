package com.storix.spring_vote_22nd.api.vote.usecase;

import com.storix.spring_vote_22nd.UseCase;
import com.storix.spring_vote_22nd.domains.vote.adaptor.VoteAdaptor;
import com.storix.spring_vote_22nd.domains.vote.domain.Candidate;
import com.storix.spring_vote_22nd.domains.vote.domain.VoteCategory;
import com.storix.spring_vote_22nd.domains.vote.dto.CandidateResponse;
import com.storix.spring_vote_22nd.domains.vote.dto.VoteRequest;
import com.storix.spring_vote_22nd.domains.vote.service.VoteService;
import com.storix.spring_vote_22nd.global.apiPayload.CustomResponse;
import com.storix.spring_vote_22nd.global.apiPayload.code.SuccessCode;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class VoteUseCase {

    private final VoteService voteService;
    private final VoteAdaptor voteAdaptor;

    public CustomResponse<String> vote(Long userId, VoteRequest req) {
        voteService.castVote(userId, req.candidateId());
        return CustomResponse.onSuccess(SuccessCode.CREATED, "투표 완료");
    }

    // 후보 목록 조회 (득표순)
    public CustomResponse<List<CandidateResponse>> getCandidates(VoteCategory category) {
        List<Candidate> candidates = voteAdaptor.findAllCandidatesByCategory(category);

        List<CandidateResponse> result = candidates.stream()
                .map(candidate -> {
                    // Redis에서 실시간 득표수 조회
                    Long votes = voteAdaptor.getVoteCount(category, candidate.getId());
                    return CandidateResponse.of(candidate, votes);
                })
                // 득표수 기준 내림차순 정렬
                .sorted(Comparator.comparing(CandidateResponse::voteCount).reversed())
                .collect(Collectors.toList());

        return CustomResponse.onSuccess(SuccessCode.SUCCESS, result);
    }
}