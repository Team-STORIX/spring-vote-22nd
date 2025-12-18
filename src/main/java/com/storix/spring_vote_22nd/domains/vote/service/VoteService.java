package com.storix.spring_vote_22nd.domains.vote.service;

import com.storix.spring_vote_22nd.domains.user.adaptor.UserAdaptor;
import com.storix.spring_vote_22nd.domains.user.domain.User;
import com.storix.spring_vote_22nd.domains.vote.adaptor.VoteAdaptor;
import com.storix.spring_vote_22nd.domains.vote.domain.Candidate;
import com.storix.spring_vote_22nd.domains.vote.domain.VoteCategory;
import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;
import com.storix.spring_vote_22nd.global.apiPayload.exception.STORIXCodeException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteAdaptor voteAdaptor;
    private final UserAdaptor userAdaptor;

    @Transactional
    public void castVote(Long userId, Long candidateId) {
        User user = userAdaptor.findUserById(userId);
        Candidate candidate = voteAdaptor.findCandidateById(candidateId);

        validatePermissions(user, candidate);

        boolean isFirstVote = voteAdaptor.setVoteHistoryIfAbsent(userId, candidate.getCategory());
        if (!isFirstVote) {
            throw new STORIXCodeException(ErrorCode.ALREADY_VOTED);
        }

        voteAdaptor.addVoteCount(candidate.getCategory(), candidateId);
    }

    private void validatePermissions(User user, Candidate candidate) {
        if (candidate.getCategory() == VoteCategory.PART_LEADER) {
            // 파트장 투표: 본인 파트의 후보에게만 투표 가능
            if (user.getPart() != candidate.getPart()) {
                throw new STORIXCodeException(ErrorCode.FORBIDDEN);
            }
        } else if (candidate.getCategory() == VoteCategory.DEMO_DAY) {
            // 데모데이 투표: 본인 팀을 제외하고 투표 가능
            if (user.getTeam() == candidate.getTeam()) {
                throw new STORIXCodeException(ErrorCode.FORBIDDEN);
            }
        }
    }
}