package com.storix.spring_vote_22nd.api.auth.usecase;

import com.storix.spring_vote_22nd.UseCase;
import com.storix.spring_vote_22nd.api.auth.dto.SignupRequest;
import com.storix.spring_vote_22nd.api.auth.dto.SignupResponse;
import com.storix.spring_vote_22nd.domains.user.service.AuthService;
import com.storix.spring_vote_22nd.global.apiPayload.CustomResponse;
import com.storix.spring_vote_22nd.global.apiPayload.code.SuccessCode;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AuthUseCase {

    private final AuthService authService;

    public CustomResponse<SignupResponse> signup(SignupRequest req) {
        Long userId = authService.signUp(req);
        return CustomResponse.onSuccess(SuccessCode.SUCCESS,
                new SignupResponse(userId, req.loginId(), req.name()));
    }
}
