package com.storix.spring_vote_22nd.api.auth.usecase;

import com.storix.spring_vote_22nd.UseCase;
import com.storix.spring_vote_22nd.api.auth.dto.PartResponse;
import com.storix.spring_vote_22nd.api.auth.dto.TeamResponse;
import com.storix.spring_vote_22nd.domains.user.domain.Part;
import com.storix.spring_vote_22nd.domains.user.domain.Team;
import com.storix.spring_vote_22nd.global.apiPayload.CustomResponse;
import com.storix.spring_vote_22nd.global.apiPayload.code.SuccessCode;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class DropDownUseCase {

    public CustomResponse<List<TeamResponse>> getTeams() {
        List<TeamResponse> result = Arrays.stream(Team.values())
                .map(TeamResponse::from)
                .toList();

        return CustomResponse.onSuccess(SuccessCode.SUCCESS, result);
    }

    public CustomResponse<List<PartResponse>> getParts() {
        List<PartResponse> result = Arrays.stream(Part.values())
                .map(PartResponse::from)
                .toList();

        return CustomResponse.onSuccess(SuccessCode.SUCCESS, result);
    }

}
