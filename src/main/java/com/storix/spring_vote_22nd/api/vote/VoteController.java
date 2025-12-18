package com.storix.spring_vote_22nd.api.vote;

import com.storix.spring_vote_22nd.api.vote.usecase.VoteUseCase;
import com.storix.spring_vote_22nd.domains.user.adaptor.AuthUserDetails;
import com.storix.spring_vote_22nd.domains.vote.domain.VoteCategory;
import com.storix.spring_vote_22nd.domains.vote.dto.VoteRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteUseCase voteUseCase;

    @Operation(summary = "투표하기 API (로그인 필요)")
    @PostMapping
    public ResponseEntity vote(
            @AuthenticationPrincipal AuthUserDetails authUser,
            @RequestBody VoteRequest req
    ) {
        if (authUser == null) {
            return ResponseEntity.status(401).build();
        }
        Long userId = Long.parseLong(authUser.getUserId());
        return ResponseEntity.ok(voteUseCase.vote(userId, req));
    }

    @Operation(summary = "파트장 투표 후보 목록 조회 (득표순 정렬)")
    @GetMapping("/part-leader")
    public ResponseEntity getPartLeaderCandidates() {
        return ResponseEntity.ok(voteUseCase.getCandidates(VoteCategory.PART_LEADER));
    }

    @Operation(summary = "데모데이 투표 후보 목록 조회 (득표순 정렬)")
    @GetMapping("/demo-day")
    public ResponseEntity getDemoDayCandidates() {
        return ResponseEntity.ok(voteUseCase.getCandidates(VoteCategory.DEMO_DAY));
    }
}