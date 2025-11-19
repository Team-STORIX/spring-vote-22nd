package com.storix.spring_vote_22nd.api.auth.usecase;

import com.storix.spring_vote_22nd.UseCase;
import com.storix.spring_vote_22nd.api.auth.dto.AuthorizationResponse;
import com.storix.spring_vote_22nd.api.auth.dto.RefreshTokenRequest;
import com.storix.spring_vote_22nd.domains.user.adaptor.TokenGenerateHelper;
import com.storix.spring_vote_22nd.global.apiPayload.CustomResponse;
import com.storix.spring_vote_22nd.global.apiPayload.code.SuccessCode;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AuthorizationUseCase {

    private final TokenGenerateHelper tokenGenerateHelper;

    public CustomResponse<AuthorizationResponse> getAccessTokenWithRefreshToken(RefreshTokenRequest req) {
        String accessToken = tokenGenerateHelper.reissueAccessTokenWithRefreshToken(req.refreshToken());
        AuthorizationResponse result = new AuthorizationResponse(accessToken);
        return CustomResponse.onSuccess(SuccessCode.SUCCESS_REISSUE_ACCESSTOKEN, result);
    }
}
